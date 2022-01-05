package com.mediscreen.UI.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    
    private String id;

    @NotBlank
    private String date;

    @NotBlank
    private String observation;

    private Integer ownerId;
}
