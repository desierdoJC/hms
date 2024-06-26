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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    //Get Individual Employee
    @GetMapping("/patients/{id}")
    @ResponseBody
    public PatientDto getPatient(@PathVariable("id") Long patientId){
        PatientDto patientDto = patientService.getPatientById(patientId);

        return patientDto;
    }

    @GetMapping("/patients")
    public String showAllPatients(Model model){
        
        List<PatientDto> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);

        PatientDto addPatient = new PatientDto();
        model.addAttribute("addPatient", addPatient);

        PatientDto editPatient = new PatientDto();
        model.addAttribute("editPatient", editPatient);

        return "patients";
    }

    @DeleteMapping("/patients/delete/{id}")
    @ResponseBody
    public String deletePatient(@PathVariable("id") Long patientId){
        patientService.deletePatient(patientId);

        return "redirect:/patients?delete_success";
    }

    @PostMapping("/patients/update/{id}")
    public String updatepatient(@Valid @ModelAttribute("editPatient") PatientDto patientDto, 
                                @PathVariable("id") Long patientId) {

        logger.info("Updating patient with id:" + patientId);
        
        try{
            patientService.updatePatient(patientId, patientDto);
        }catch(Exception e){
            return "redirect:/patients?update_failure";
        }
        

        return "redirect:/patients?update_success";
    }
    
}
