package com.msa.license.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicenseRequest {
    @NotBlank(message = "라이센스 이름은 필수입니다.")
    @Size(min = 2, max = 30, message = "라이센스 이름은 2~30자 사이여야 합니다.")
    private String licensesName;
}
