package com.swhackathon.peggi.controller;

import com.swhackathon.peggi.dto.CollegeResponse;
import com.swhackathon.peggi.service.SuwonNaviService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("suwon-navi")
public class SuwonNaviController {
    private final SuwonNaviService suwonNaviService;

    @GetMapping("/colleges")
    public ResponseEntity<CollegeResponse> getBuildingVariables() {
        return ResponseEntity.ok(suwonNaviService.getColleges());
    }

    @GetMapping
    public ResponseEntity<Object> getPathTime(@RequestParam List<String> buildings) {
        return suwonNaviService.calculatePathTime(buildings);
    }

}
