package io.nichan.hms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.nichan.hms.dto.PatientDto;
import io.nichan.hms.dto.UserDto;
import io.nichan.hms.entity.Patient;
import io.nichan.hms.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class PatientController {
    private PatientService patientService;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    //Add Patient Form Submit Request
    @PostMapping("/patients/save")
    public String insertPatient(@Valid @ModelAttribute("addPatient") PatientDto patientDto, BindingResult result, Model model){
        Patient existingPatient = patientService.findPatientByEmail(patientDto.getEmail());
        if(existingPatient != null && existingPatient.getEmail() != null && !existingPatient.getEmail().isEmpty()){
            result.rejectValue("email", null, "Patient already added");
            return "redirect:/patients?failure";
        }
        patientService.createPatient(patientDto);
        return "redirect:/patients?success";
    }

    @GetMapping("/patients")
    public String showAllPatients(Model model){
        
        //Save all Patient details
        List<PatientDto> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);

        //Store Patient Data into a model
        PatientDto addPatient = new PatientDto();
        model.addAttribute("addPatient", addPatient);

        return "patients";
    }

    @DeleteMapping("/patients/delete/{id}")
    @ResponseBody
    public String deletePatient(@PathVariable("id") Long patientId){
        patientService.deletePatient(patientId);

        return "redirect:/patients?delete_success";
    }
}
