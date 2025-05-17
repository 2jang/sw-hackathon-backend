package com.swhackathon.peggi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "click_table")
public class Click {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clickId;

    String major;

    Integer clickNum;
}
