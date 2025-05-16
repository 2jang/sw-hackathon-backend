package com.swhackathon.peggi.service;

import com.swhackathon.peggi.domain.Click;
import com.swhackathon.peggi.dto.ClickResponse;
import com.swhackathon.peggi.repository.ClickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClickService {
    private final ClickRepository clickRepository;

    @Transactional
    public Click incrementClick(Long clickId) {
        Click click = clickRepository.findById(clickId).orElseThrow();
        click.setClickNum(click.getClickNum() + 1);
        return clickRepository.save(click);
    }

    public List<ClickResponse> getClickNum(){
        List<Click> clicks = clickRepository.findAll();

        return clicks.stream()
                .map(click -> {
                    ClickResponse response = new ClickResponse();
                    response.setClinkId(click.getClickId());
                    response.setMajor(click.getMajor());
                    response.setClickNum(click.getClickNum());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
