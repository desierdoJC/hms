package io.nichan.hms.mapper;

import io.nichan.hms.dto.PatientDto;
import io.nichan.hms.entity.Patient;

public class PatientMapper {
    public static PatientDto mapToPatientDto(Patient patient){
        return new PatientDto(
            patient.getPatient_id(),
            patient.getFirst_name(),
            patient.getLast_name(),
            patient.getBirth_date(),
            patient.getSex(),
            patient.getPhone(),
            patient.getEmail(),
            patient.getInsurance_info(),
            patient.getAge()
        );
        
    }

    public static Patient mapToPatient(PatientDto patientDto){
        return new Patient(
            patientDto.getPatient_id(),
            patientDto.getFirst_name(),
            patientDto.getLast_name(),
            patientDto.getBirth_date(),
            patientDto.getSex(),
            patientDto.getPhone(),
            patientDto.getEmail(),
            patientDto.getInsurance_info(),
            patientDto.getAge()
        );
        
    }
}
