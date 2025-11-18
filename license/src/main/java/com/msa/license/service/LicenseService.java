package com.msa.license.service;

import com.msa.license.domain.License;
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

    public List<LicenseResponse> getAllLicenses() {
        return licenseRepository.findAll().stream()
                .map(LicenseResponse::from)
                .collect(Collectors.toList());
    }

    public LicenseResponse getLicenseById(Long id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("라이선스가 없습니다."));
        return LicenseResponse.from(license);
    }

    public LicenseResponse getLicenseByName(String name) {
        License license = licenseRepository.findByLicenseName(name)
                .orElseThrow(() -> new IllegalArgumentException("라이선스가 없습니다."));
        return LicenseResponse.from(license);
    }

    @Transactional
    public LicenseResponse createLicense(String licenseName) {
        if(licenseRepository.existsByLicenseName(licenseName)) {
            throw new IllegalArgumentException("라이선스가 이미 존재합니다.");
        }

        License license = new License();
        license.setLicenseName(licenseName);
        license.setCreatedDate(java.time.LocalDate.now());

        License savedLicense = licenseRepository.save(license);
        return LicenseResponse.from(savedLicense);
    }

    @Transactional
    public LicenseResponse updateLicense(Long id, String licenseName) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("라이선스가 없습니다."));

        licenseRepository.findByLicenseName(licenseName)
                .ifPresent(existing -> {
                    if(!existing.getLicenseId().equals(id)) {
                        throw new IllegalArgumentException("라이선스가 이미 있습니다.");
                    }
                });
        license.setLicenseName(licenseName);

        License updatedLicense = licenseRepository.save(license);
        return LicenseResponse.from(updatedLicense);
    }

    @Transactional
    public void deleteLicense(Long id) {
        if(!licenseRepository.existsById(id)){
            throw new IllegalArgumentException("라이선스가 없습니다.");
        }
        licenseRepository.deleteById(id);
    }

    public long count() {
        return licenseRepository.count();
    }
}