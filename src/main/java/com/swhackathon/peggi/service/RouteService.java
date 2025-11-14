package com.swhackathon.peggi.service;

import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swhackathon.peggi.dto.ChatToLlmRequest;
import com.swhackathon.peggi.dto.OpenRouterRequest;
import com.swhackathon.peggi.dto.OpenRouterRequest.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${openrouter.api.key}")
    private String openRouterApiKey;

    private final String OPENROUTER_API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final String MODEL_NAME = "google/gemma-3-4b-it:free";

    public Mono<String> streamFromLlm(ChatToLlmRequest request) {
        OpenRouterRequest.Message userMessage = new Message("user", request.getQuestion());
        OpenRouterRequest openRouterRequest = new OpenRouterRequest(
                MODEL_NAME,
                Collections.singletonList(userMessage),
                true
        );

        Flux<ServerSentEvent<String>> sseFlux = webClient.post()
                .uri(OPENROUTER_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openRouterApiKey)
                .bodyValue(openRouterRequest)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .onStatus(HttpStatusCode::isError, res ->
                        res.bodyToMono(String.class).flatMap(body -> {
                            log.error("OpenRouter API 에러: {} - {}", res.statusCode(), body);
                            return Mono.error(new RuntimeException("OpenRouter API 에러: " + res.statusCode() + " - " + body));
                        })
                )
                .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {});

        Flux<String> dataFlux = sseFlux
                .map(ServerSentEvent::data)
                .filter(Objects::nonNull)
                .map(this::stripPrefix)
                .takeUntil(line -> line.trim().equals("[DONE]"))
                .filter(line -> !line.trim().equals("[DONE]") && !line.trim().isEmpty())
                .map(this::parseOpenRouterChunk)
                .onErrorContinue((err, obj) -> log.error("스트림 처리 중 오류 발생. Data: {}, Error: {}", obj, err.getMessage(), err));

        Mono<String> streamingMono = dataFlux
                .reduce("", String::concat)
                .map(finalString -> {
                    if (finalString == null || finalString.isEmpty()) {
                        return "";
                    }
                    String processedString = finalString;
                    processedString = processedString.stripTrailing();
                    processedString = processedString.stripLeading();
                    return processedString;
                });

        return streamingMono.onErrorResume(err -> {
            log.error("SSE 스트리밍 실패로 비스트리밍으로 폴백합니다. Error: {}", err.getMessage(), err);
            return fallbackNonStreaming(openRouterRequest);
        });
    }

    private String stripPrefix(String rawEventData) {
        if (rawEventData.startsWith("data: ")) {
            return rawEventData.substring("data: ".length()).trim();
        }
        return rawEventData.trim();
    }

    private String parseOpenRouterChunk(String jsonChunk) {
        if (jsonChunk.isEmpty() || jsonChunk.equals("[DONE]")) {
            return "";
        }
        try {
            JsonNode rootNode = objectMapper.readTree(jsonChunk);
            JsonNode choicesNode = rootNode.path("choices");
            if (choicesNode.isArray() && !choicesNode.isEmpty()) {
                JsonNode firstChoice = choicesNode.get(0);
                JsonNode deltaNode = firstChoice.path("delta");
                JsonNode contentNode = deltaNode.path("content");
                if (contentNode.isTextual()) {
                    return contentNode.asText();
                }
            }
        } catch (Exception e) {
            log.error("OpenRouter 청크 파싱 오류. Chunk: '{}', Error: {}", jsonChunk, e.getMessage(), e);
        }
        return "";
    }

    private Mono<String> fallbackNonStreaming(OpenRouterRequest originalRequest) {
        OpenRouterRequest nonStreamRequest = new OpenRouterRequest(
                originalRequest.getModel(),
                originalRequest.getMessages(),
                false
        );

        return webClient.post()
                .uri(OPENROUTER_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openRouterApiKey)
                .bodyValue(nonStreamRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, res ->
                        res.bodyToMono(String.class).flatMap(body -> {
                            log.error("OpenRouter API 에러(Non-Stream): {} - {}", res.statusCode(), body);
                            return Mono.error(new RuntimeException("OpenRouter API 에러(Non-Stream): " + res.statusCode() + " - " + body));
                        })
                )
                .bodyToMono(String.class)
                .map(this::parseNonStreamingOpenRouterResponse)
                .onErrorResume(err -> {
                    log.error("Non-Stream 요청도 실패했습니다. Error: {}", err.getMessage(), err);
                    return Mono.error(err);
                });
    }

    private String parseNonStreamingOpenRouterResponse(String json) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode choicesNode = rootNode.path("choices");
            if (choicesNode.isArray() && !choicesNode.isEmpty()) {
                JsonNode firstChoice = choicesNode.get(0);
                JsonNode messageNode = firstChoice.path("message");
                JsonNode contentNode = messageNode.path("content");
                if (contentNode.isTextual()) {
                    return contentNode.asText();
                }
            }
        } catch (Exception e) {
            log.error("OpenRouter 응답 파싱 오류(Non-Stream). Body: '{}', Error: {}", json, e.getMessage(), e);
        }
        return "";
    }
}