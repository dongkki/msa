package com.msa.license.dto;

import com.msa.license.domain.License;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LicenseResponse {
    private Long licenseId;
    private String licenseName;
    private LocalDate createDate;

    public static LicenseResponse from(License license) {
        return LicenseResponse.builder()
                .licenseId(license.getLicenseId())
                .licenseName(license.getLicenseName())
                .createDate(license.getCreatedDate())
                .build();
    }
}