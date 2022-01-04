package com.mediscreen.UI.controllers;

import com.mediscreen.UI.models.Patient;
import com.mediscreen.UI.webClients.PatientClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {

    @Autowired
    private PatientClient patientClient;

    @GetMapping(value = "/patient/list")
    public String showAll(Model model) {
        model.addAttribute("patients", patientClient.getAllPatients());
        return "patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws Exception {
        model.addAttribute("patient", patientClient.getPatientById(id));
        return "patient/edit";
    }

    @PostMapping("/patient/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Validated Patient patient,
            BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            if (result.getFieldError("firstName") != null) {
                model.addAttribute("firstName", result.getFieldError("firstName").getDefaultMessage());
            }
            if (result.getFieldError("lastName") != null) {
                model.addAttribute("lastName", result.getFieldError("lastName").getDefaultMessage());
            }
            if (result.getFieldError("dateOfBirth") != null) {
                model.addAttribute("dateOfBirth", result.getFieldError("dateOfBirth").getDefaultMessage());
            }
            if (result.getFieldError("sex") != null) {
                model.addAttribute("sex", result.getFieldError("sex").getDefaultMessage());
            }
            return "patient/edit";
        } else {
            patientClient.updatePatient(patient);
            return "redirect:/patient/list";
        }
    }

    @GetMapping(value = "/patient/add")
    public String showCreateForm() {
        return "patient/add";
    }

    @PostMapping(value = "/patient/add")
    public String createUser(@Validated Patient patient, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            if (result.getFieldError("firstName") != null) {
                model.addAttribute("firstName", result.getFieldError("firstName").getDefaultMessage());
            }
            if (result.getFieldError("lastName") != null) {
                model.addAttribute("lastName", result.getFieldError("lastName").getDefaultMessage());
            }
            if (result.getFieldError("birthdate") != null) {
                model.addAttribute("birthdate", result.getFieldError("birthdate").getDefaultMessage());
            }
            if (result.getFieldError("sex") != null) {
                model.addAttribute("sex", result.getFieldError("sex").getDefaultMessage());
            }
            return "patient/add";
        } else {
            patientClient.createPatient(patient);
            return "redirect:/patient/list";
        }
    }

    @GetMapping("/patient/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) throws Exception {
        patientClient.deletePatientById(id);
        return "redirect:/patient/list";
    }
}
