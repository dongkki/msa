package com.msa.license.repository;

import com.msa.license.domain.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    Optional<License> findByLicenseName(String licenseName);
    boolean existsByLicenseName(String licenseName);
}