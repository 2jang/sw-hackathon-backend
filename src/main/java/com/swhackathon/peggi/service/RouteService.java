package com.swhackathon.peggi.service;

import com.swhackathon.peggi.dto.ChatToLlmRequest;
import com.swhackathon.peggi.dto.ChatToLlmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RestTemplate restTemplate;

    public ResponseEntity<ChatToLlmResponse> chatToLlm(ChatToLlmRequest requestBody) {
        String llmUrl = "http://ahnai1.suwon.ac.kr:5042/api/simple-chat";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatToLlmRequest> request = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForEntity(llmUrl, request, ChatToLlmResponse.class);
    }

}


