package io.nichan.hms.service;

import java.util.List;

import io.nichan.hms.dto.DoctorDto;

public interface DoctorService {
    void createDoctor(DoctorDto doctorDto);

    List<DoctorDto> findAllDoctors();
}
