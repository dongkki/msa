package com.msa.license.controller;

import com.msa.license.dto.LicenseRequest;
import com.msa.license.dto.LicenseResponse;
import com.msa.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping
    public ResponseEntity<List<LicenseResponse>> getAllLicenses() {
        List<LicenseResponse> licenses = licenseService.getAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    @GetMapping("{id}")
    public ResponseEntity<LicenseResponse> getLicenseById(@PathVariable Long id) {
        LicenseResponse licenseResponse = licenseService.getLicenseById(id);
        return ResponseEntity.ok(licenseResponse);
    }

    @GetMapping("{name}")
    public ResponseEntity<LicenseResponse> getLicenseByName(@PathVariable String name) {
        LicenseResponse licenseResponse = licenseService.getLicenseByName(name);
        return ResponseEntity.ok(licenseResponse);
    }

    @PostMapping
    public ResponseEntity<LicenseResponse> createLicense(@RequestBody LicenseRequest licenseRequest) {
        LicenseResponse licenseResponse = licenseService.createLicense(licenseRequest.getLicenseName());
        return ResponseEntity.ok(licenseResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<LicenseResponse> updateLicense(@PathVariable Long id, @RequestBody String licenseName) {
        LicenseResponse licenseResponse = licenseService.updateLicense(id, licenseName);
        return ResponseEntity.ok(licenseResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long id) {
        licenseService.deleteLicense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("count")
    public ResponseEntity<Long> count() {
        long count = licenseService.count();
        return ResponseEntity.ok(count);
    }
}