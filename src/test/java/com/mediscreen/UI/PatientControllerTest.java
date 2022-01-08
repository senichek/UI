package com.mediscreen.UI;

import com.mediscreen.UI.controllers.PatientController;
import com.mediscreen.UI.models.Patient;
import com.mediscreen.UI.webClients.PatientFeignClient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientFeignClient patientFeignClient;

    @Test
    public void showAllTest() throws Exception {
        mockMvc.perform(get("/patient/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/list"))
                .andDo(print());
    }

    @Test
    public void showUpdateFormTest() throws Exception {
        Patient patient = new Patient();
        when(patientFeignClient.getPatientById(any(Integer.class))).thenReturn(patient);
        mockMvc.perform(get("/patient/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/edit"))
                .andDo(print());
    }

    @Test
    public void updateUserTest() throws Exception {
        // There is no @RequestBody in Controller, this is why we can pass
        // the Note's attributes as URL params
        mockMvc.perform(post("/patient/update/1?firstName=John&lastName=Doe&birthdate=2002-01-01&sex=M"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/list"))
                .andDo(print());
    }

    @Test
    public void updateUserFailTest() throws Exception {
        // There is no @RequestBody in Controller, this is why we can pass
        // the Note's attributes as URL params
        mockMvc.perform(post("/patient/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/edit"))
                .andDo(print());
    }

    @Test
    public void showCreateFormTest() throws Exception {
        mockMvc.perform(get("/patient/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/add"))
                .andDo(print());
    }

    @Test
    public void createUserTest() throws Exception {
        // There is no @RequestBody in Controller, this is why we can pass
        // the Note's attributes as URL params
        mockMvc.perform(post("/patient/add?firstName=John&lastName=Doe&birthdate=2002-01-01&sex=M"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/list"))
                .andDo(print());
    }

    @Test
    public void createUserFailTest() throws Exception {
        mockMvc.perform(post("/patient/add/"))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/add"))
                .andDo(print());
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(get("/patient/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patient/list"))
                .andDo(print());
    }
}
