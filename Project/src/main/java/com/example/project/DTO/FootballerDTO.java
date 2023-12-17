package com.example.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootballerDTO {
    private long id;
    private String name;
    private int number;
    private String club;

    public FootballerDTO(String name, int number, String club) {
        this.name = name;
        this.number = number;
        this.club = club;
    }
}
