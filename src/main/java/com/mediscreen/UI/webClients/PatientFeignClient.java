package com.mediscreen.UI.webClients;

import java.util.List;

import com.mediscreen.UI.models.Patient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ui-patients", url = "localhost:8080")
public interface PatientFeignClient {

    @GetMapping(value = "/rest/patient/all")
    List<Patient> getAllPatients();

    @GetMapping(value = "/rest/patient/{id}")
    Patient getPatientById(@PathVariable("id") Integer id);

    @PostMapping(value = "/rest/patient/add")
    Patient createPatient(@RequestBody Patient patient);

    @PostMapping(value = "/rest/patient/update")
    Patient updatePatient(@RequestBody Patient patient);

    @DeleteMapping(value = "/rest/patient/delete/{id}")
    Patient deletePatientById(@PathVariable("id") Integer id);
}
