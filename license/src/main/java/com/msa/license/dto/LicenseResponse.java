package com.msa.license.dto;

import com.msa.license.domain.License;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LicenseResponse {  // 도메인 생성 > repository > service > controller
    private Long licenseId;
    private String licenseName;
    private LocalDate createDate;

    public static LicenseResponse from(License license){
        return LicenseResponse.builder()
                .licenseId(license.getLicenseId())
                .licenseName(license.getLicenseName())
                .createDate(license.getCreateDate())
                .build();
    }
}
