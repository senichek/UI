package com.mediscreen.UI.webClients;

import java.util.List;

import com.mediscreen.UI.models.Patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ui-patients", url = "localhost:8080")
public interface PatientClient {
    @GetMapping(value = "/rest/patient/all")
    List<Patient> getAllPatients();

    @GetMapping(value = "/rest/patient/{id}")
    Patient getPatientById(@PathVariable("id") Integer id);
}
