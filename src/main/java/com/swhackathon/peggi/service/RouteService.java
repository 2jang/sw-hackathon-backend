package com.swhackathon.peggi.service;

import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swhackathon.peggi.dto.ChatToLlmRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public Mono<String> streamFromLlm(ChatToLlmRequest request) {
        return webClient.post()
                .uri("http://ahnai1.suwon.ac.kr:5042/api/stream-chat")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, res ->
                        res.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new RuntimeException("4xx 에러: " + body)))
                )
                .onStatus(HttpStatusCode::is5xxServerError, res ->
                        res.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new RuntimeException("5xx 에러: " + body)))
                )
                .bodyToFlux(String.class)
                .onErrorContinue((err, obj) -> log.error("Stream 오류", err))
                // 후처리
                .map(this::stripPrefix)
                .map(this::parseChunk)
                .takeUntil(Pair::getRight)
                .map(Pair::getLeft)
                .reduce("", String::concat);
    }

    private String stripPrefix(String raw) {
        return raw.replaceFirst("^data:\\s*", "").trim();
    }

    private Pair<String, Boolean> parseChunk(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode chunkNode = root.get("chunk");
            JsonNode doneNode = root.get("done");
            if (chunkNode == null || doneNode == null) throw new IllegalArgumentException("Missing fields");
            return Pair.of(chunkNode.asText(), doneNode.asBoolean());
        } catch (Exception e) {
            log.error("Chunk 파싱 오류", e);
            return Pair.of("", true); // 오류 발생 시 스트림 종료 신호
        }
    }
}
