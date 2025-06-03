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
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${openrouter.api.key}")
    private String openRouterApiKey;

    private final String OPENROUTER_API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final String MODEL_NAME = "google/gemma-3-1b-it:free";

    public Mono<String> streamFromLlm(ChatToLlmRequest request) {
        OpenRouterRequest.Message userMessage = new Message("user", request.getQuestion());
        OpenRouterRequest openRouterRequest = new OpenRouterRequest(
                MODEL_NAME,
                Collections.singletonList(userMessage),
                true
        );

        return webClient.post()
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
                .bodyToFlux(String.class)
                .map(this::stripPrefix)
                .takeUntil(line -> line.trim().equals("[DONE]"))
                .filter(line -> !line.trim().equals("[DONE]") && !line.trim().isEmpty())
                .map(this::parseOpenRouterChunk)
                .onErrorContinue((err, obj) -> log.error("스트림 처리 중 오류 발생. Data: {}, Error: {}", obj, err.getMessage(), err))
                .reduce("", String::concat) // 모든 청크를 하나의 문자열로 합침
                .map(finalString -> {
                    if (finalString == null || finalString.isEmpty()) {
                        return ""; // 또는 상황에 따라 null 반환
                    }
                    String processedString = finalString;

                    // 1. 문자열 끝의 모든 공백(줄바꿈 포함) 제거
                    processedString = processedString.stripTrailing();

                    // 2. 문자열 시작의 모든 공백(줄바꿈 포함) 제거
                    processedString = processedString.stripLeading();
                    return processedString;
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
}