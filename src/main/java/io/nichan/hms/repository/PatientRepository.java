package io.nichan.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nichan.hms.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    Patient findByEmail(String email);
}
