package io.nichan.hms.mapper;

import io.nichan.hms.dto.DoctorDto;
import io.nichan.hms.entity.Doctor;

public class DoctorMapper {
    public static DoctorDto mapToDoctorDto(Doctor doctor){
        return new DoctorDto(
            doctor.getDoctor_id(),
            doctor.getFirst_name(),
            doctor.getLast_name(),
            doctor.getEmail(),
            doctor.getSpecialization(),
            doctor.getSchedule()
        );
    }

    public static Doctor mapToDoctor(DoctorDto doctorDto){
        return new Doctor(
            doctorDto.getDoctor_id(),
            doctorDto.getFirst_name(),
            doctorDto.getLast_name(),
            doctorDto.getEmail(),
            doctorDto.getSpecialization(),
            doctorDto.getSchedule()
        );
    }
}
