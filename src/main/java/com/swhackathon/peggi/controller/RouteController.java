package com.swhackathon.peggi.controller;

import com.swhackathon.peggi.dto.ChatToLlmRequest;
import com.swhackathon.peggi.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> chatStream(@RequestBody ChatToLlmRequest request) {
        return routeService.streamFromLlm(request);
    }
}
