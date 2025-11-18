package com.msa.license.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@Entity
@Table(name="licenses")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이블 생성 시 키에 주어진 index 설정
    @Column
    private Long licenseId;

    @Column
    private String licenseName;

    @Column
    private LocalDate createdDate;
}
