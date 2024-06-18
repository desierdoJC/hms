package io.nichan.hms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.nichan.exception.ResourceNotFoundException;
import io.nichan.hms.dto.PatientDto;
import io.nichan.hms.entity.Patient;
import io.nichan.hms.entity.User;
import io.nichan.hms.mapper.PatientMapper;
import io.nichan.hms.repository.PatientRepository;
import io.nichan.hms.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    
    @Override
    public void createPatient(PatientDto patientDto) {
        Patient patient = PatientMapper.mapToPatient(patientDto);
        patientRepository.save(patient);

    }

    @Override
    public Patient findPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public List<PatientDto> findAllPatients() {
        List<Patient> allPatients = patientRepository.findAll();
        return allPatients.stream().map((patient) -> PatientMapper.mapToPatientDto(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(Long patientId, PatientDto updatedPatient) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
            () -> new ResourceNotFoundException("Patient does not exist with the given ID:" + patientId)
        );

        patient.setFirst_name(updatedPatient.getFirst_name());
        patient.setLast_name(updatedPatient.getLast_name());
        patient.setBirth_date(updatedPatient.getBirth_date());
        patient.setSex(updatedPatient.getSex());
        patient.setPhone(updatedPatient.getPhone());
        patient.setEmail(updatedPatient.getEmail());
        patient.setInsurance_info(updatedPatient.getInsurance_info());

        Patient updatedPatientObj = patientRepository.save(patient);

        return PatientMapper.mapToPatientDto(updatedPatientObj);

    }

    @Override
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
            () -> new ResourceNotFoundException("Patient does not exist with the given ID:" + patientId)
        );
        patientRepository.deleteById(patientId);
    }

    @Override
    public PatientDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
            () -> new ResourceNotFoundException("Patient does not exist with the given ID:" + patientId)
        );

        return PatientMapper.mapToPatientDto(patient);
    }

}
