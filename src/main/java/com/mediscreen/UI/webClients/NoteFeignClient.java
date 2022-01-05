package com.mediscreen.UI.webClients;

import java.util.List;

import com.mediscreen.UI.models.Note;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ui-notes", url = "localhost:8081")
public interface NoteFeignClient {

    /* @PostMapping(value = "/patHistory/add/{userId}")
    public ResponseEntity<Note> createNote(@PathVariable("userId") Integer id, @RequestBody Note note) throws Exception {
        return new ResponseEntity<>(noteService.create(note, id), HttpStatus.OK);
    } */

    @PostMapping(value = "/rest/patHistory/add/{userId}")
    Note createNote(@PathVariable("userId") Integer userId, @RequestBody Note note);

    @GetMapping(value = "/rest/patHistory/{userId}")
    List<Note> getNotesByUserId(@PathVariable("userId") Integer id);

    @GetMapping(value = "/rest/patHistory/note/{noteId}")
    Note getNoteById(@PathVariable("noteId") String noteId);

    @PostMapping(value = "/rest/patHistory/update/{userId}")
    Note updateNote(@PathVariable("userId") Integer userId, @RequestBody Note note);

    @GetMapping(value = "/rest/patHistory/delete/{noteId}")
    Note deleteNote(@PathVariable("noteId") String noteId);
}
