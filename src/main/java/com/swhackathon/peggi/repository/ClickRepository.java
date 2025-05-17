package com.swhackathon.peggi.repository;

import com.swhackathon.peggi.domain.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClickRepository extends JpaRepository<Click, Long> {
    @Modifying
    @Query("UPDATE Click c SET c.clickNum = c.clickNum + 1 WHERE c.clickId = :clickId")
    void incrementClickNum(@Param("clickId") Long clickId);
}
