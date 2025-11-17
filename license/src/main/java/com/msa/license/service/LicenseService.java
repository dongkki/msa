package com.msa.license.service;

import com.msa.license.domain.License;
import com.msa.license.dto.LicenseRequest;
import com.msa.license.dto.LicenseResponse;
import com.msa.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseService {
    private final LicenseRepository licenseRepository;

    public List<LicenseResponse> getLicenses() {
        return licenseRepository.findAll().stream()
                .map(LicenseResponse::from)
                .collect(Collectors.toList());
    }

    public LicenseResponse getLicenseById(Long id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("라이센스가 없습니다."));
        return LicenseResponse.from(license);
    }

    public LicenseResponse getLicenseByName(String name) {
        License license = licenseRepository.findByLicenseName(name)
                .orElseThrow(() -> new IllegalArgumentException("라이센스가 없습니다."));
        return LicenseResponse.from(license);
    }

    @Transactional
    public LicenseResponse createLicense(LicenseRequest request) {
        if (licenseRepository.existsByLicenseName(request.getLicensesName())) {
            throw new IllegalArgumentException("이미 존재하는 라이센스입니다.");
        }
    }
}
