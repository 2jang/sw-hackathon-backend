package com.swhackathon.peggi.controller;

import com.swhackathon.peggi.domain.Click;
import com.swhackathon.peggi.dto.ClickMessage;
import com.swhackathon.peggi.dto.ClickResponse;
import com.swhackathon.peggi.service.ClickService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClickController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ClickService clickService;

    @MessageMapping("/click") // /app/click 로 메시지 오면 실행됨
    public void handleClick(ClickMessage message) {
        Click updated = clickService.incrementClick(message.getClickId());

        ClickMessage response = new ClickMessage();
        response.setClickId(updated.getClickId());
        response.setClickNum(updated.getClickNum());

        messagingTemplate.convertAndSend("/topic/clicks", response); // 모든 구독자에게 브로드캐스트
    }

    @GetMapping("/click-num")
    public List<ClickResponse> getAllClickNums() {
        return clickService.getClickNum();
    }
}
