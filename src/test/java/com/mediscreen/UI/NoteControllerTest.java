package com.mediscreen.UI;

import com.mediscreen.UI.controllers.NoteController;
import com.mediscreen.UI.models.Note;
import com.mediscreen.UI.models.Patient;
import com.mediscreen.UI.webClients.NoteFeignClient;
import com.mediscreen.UI.webClients.PatientFeignClient;
import com.mediscreen.UI.webClients.ReportFeignClient;

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

@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteFeignClient noteFeignClient;

    @MockBean
    private PatientFeignClient patientFeignClient;

    @MockBean
    private ReportFeignClient reportFeignClient;

    @Test
    public void showHistoryTest() throws Exception {
        mockMvc.perform(get("/patHistory/1?generateReport=true"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/list"))
                .andDo(print());
    }

    @Test
    public void showUpdateFormTest() throws Exception {
        Note note = new Note();
        when(noteFeignClient.getNoteById(any(String.class))).thenReturn(note);
        mockMvc.perform(get("/patHistory/update/61d1b70cb1f500126871bc4e"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/edit"))
                .andDo(print());
    }

    @Test
    public void editNoteTest() throws Exception {
        // There is no @RequestBody in Controller, this is why we can pass
        // the Note's attributes as URL params
        mockMvc.perform(post("/patHistory/update/61d1b70cb1f500126871bc4e?date=2022-01-01&observation=Test&ownerId=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patHistory/1"))
                .andDo(print());
    }

    @Test
    public void editNoteFailTest() throws Exception {
        mockMvc.perform(post("/patHistory/update/61d1b70cb1f500126871bc4e"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/edit"))
                .andDo(print());
    }

    @Test
    public void deleteNoteTest() throws Exception {
        mockMvc.perform(get("/patHistory/delete/1/61d1b70cb1f500126871bc4e"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patHistory/1"))
                .andDo(print());
    }

    @Test
    public void showCreateFormTest() throws Exception {
        Patient patient = new Patient();
        when(patientFeignClient.getPatientById(any(Integer.class))).thenReturn(patient);
        mockMvc.perform(get("/patHistory/add/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/add"))
                .andDo(print());
    }

    @Test
    public void createNoteTest() throws Exception {
        mockMvc.perform(post("/patHistory/add/1?date=2022-01-01&observation=Test&ownerId=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/patHistory/1"))
                .andDo(print());
    }

    @Test
    public void createNoteFailTest() throws Exception {
        // There are patient variables in thymeleaf's view ("note/add") this is why
        // we need the Patient object (we mock it).
        Patient patient = new Patient();
        when(patientFeignClient.getPatientById(any(Integer.class))).thenReturn(patient);
        mockMvc.perform(post("/patHistory/add/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("note/add"))
                .andDo(print());
    }
}
