package com.swhackathon.peggi.data;

import com.swhackathon.peggi.domain.Click;
import com.swhackathon.peggi.repository.ClickRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class SeedData {

    private final ClickRepository clickRepository;

    @PostConstruct
    public void init(){
        initClick();;
    }

    public void initClick(){
        Click click1 = Click.builder()
                .major("정보통신학부")
                .clickNum(43)
                .build();

        Click click2 = Click.builder()
                .major("데이터과학부")
                .clickNum(31)
                .build();

        Click click3 = Click.builder()
                .major("컴퓨터학부")
                .clickNum(27)
                .build();

        clickRepository.save(click1);
        clickRepository.save(click2);
        clickRepository.save(click3);
    }
}
