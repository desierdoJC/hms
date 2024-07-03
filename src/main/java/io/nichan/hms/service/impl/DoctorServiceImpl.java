package io.nichan.hms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.nichan.hms.dto.DoctorDto;
import io.nichan.hms.entity.Doctor;
import io.nichan.hms.mapper.DoctorMapper;
import io.nichan.hms.repository.DoctorRepository;
import io.nichan.hms.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void createDoctor(DoctorDto doctorDto) {
        Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
        doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> allDoctors = doctorRepository.findAll();
        return allDoctors.stream().map((doctor) -> 
        DoctorMapper.mapToDoctorDto(doctor)).collect(Collectors.toList());
    }

}
