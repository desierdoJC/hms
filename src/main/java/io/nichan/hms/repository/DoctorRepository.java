package io.nichan.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nichan.hms.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    Doctor findByEmail(String email);

}
