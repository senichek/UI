package com.mediscreen.UI.controllers;

import com.mediscreen.UI.webClients.PatientClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
