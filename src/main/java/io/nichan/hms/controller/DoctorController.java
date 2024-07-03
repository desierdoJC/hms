package io.nichan.hms.controller;

import org.springframework.stereotype.Controller;

import io.nichan.hms.service.PatientService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class DoctorController {
    private PatientService patientService;


}
