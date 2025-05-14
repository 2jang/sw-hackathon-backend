package com.swhackathon.peggi.controller;

import com.swhackathon.peggi.dto.ChatToLlmRequest;
import com.swhackathon.peggi.dto.ChatToLlmResponse;
import com.swhackathon.peggi.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;

    @PostMapping()
    public ResponseEntity<ChatToLlmResponse> chatToLlm(@RequestBody ChatToLlmRequest requestBody) {
        ResponseEntity<ChatToLlmResponse> llmResponse = routeService.chatToLlm(requestBody);
        return ResponseEntity.status(llmResponse.getStatusCode()).body(llmResponse.getBody());
    }
}
