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

    //Finds a patient entity from db using email
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePatient'");
    }

    @Override
    public void deletePatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
            () -> new ResourceNotFoundException("Patient does not exist with the given ID:" + patientId)
        );
        patientRepository.deleteById(patientId);
    }

}
