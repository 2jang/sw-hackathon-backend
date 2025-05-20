package com.swhackathon.peggi.config;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClickRateLimiter {

    private final Map<Long, RequestCounter> clickRequestMap = new ConcurrentHashMap<>();

    private static class RequestCounter {
        private int count;
        private Instant windowStart;

        RequestCounter() {
            this.count = 0;
            this.windowStart = Instant.now();
        }

        synchronized boolean allowRequest() {
            Instant now = Instant.now();
            if (now.isAfter(windowStart.plusSeconds(1))) {
                // 1초 지나면 초기화
                windowStart = now;
                count = 1;
                return true;
            } else {
                if (count < 20) {
                    count++;
                    return true;
                }
                return false;
            }
        }
    }

    public boolean tryAcquire(Long clickId) {
        return clickRequestMap.computeIfAbsent(clickId, id -> new RequestCounter())
                .allowRequest();
    }
}

