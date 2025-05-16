package com.swhackathon.peggi.repository;

import com.swhackathon.peggi.domain.Click;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<Click, Long> {
}
