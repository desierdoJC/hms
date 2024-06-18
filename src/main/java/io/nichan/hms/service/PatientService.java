package io.nichan.hms.service;

import java.util.List;

import io.nichan.hms.dto.PatientDto;
import io.nichan.hms.entity.Patient;

public interface PatientService {
    void createPatient(PatientDto patientDto);

    List<PatientDto> findAllPatients();

    Patient findPatientByEmail(String email);

    PatientDto updatePatient(Long patientId, PatientDto updatedPatient);

    void deletePatient(Long patientId);

    PatientDto getPatientById(Long patientId);
}
