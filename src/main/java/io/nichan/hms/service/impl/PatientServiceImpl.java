package io.nichan.hms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        return allPatients.stream().map((patient) -> mapToPatientDto(patient)).collect(Collectors.toList());
    }

    private PatientDto mapToPatientDto(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setPatient_id(patient.getPatient_id());
        patientDto.setFirst_name(patient.getFirst_name());
        patientDto.setLast_name(patient.getLast_name());
        patientDto.setBirth_date(patient.getBirth_date());
        patientDto.setSex(patient.getBirth_date());
        patientDto.setPhone(patient.getPhone());
        patientDto.setEmail(patient.getEmail());
        patientDto.setInsurance_info(patient.getInsurance_info());
        return patientDto;

    }

}
