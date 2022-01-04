package com.mediscreen.UI.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    
    protected Integer id;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String sex;

    private String address;

    private String phone;
}
