package com.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "footballers")
public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "club", nullable = false)
    private String club;
    // @JsonIgnore
    @Column(name = "born", nullable = false)
    private String born;
    @Column(name = "retired", nullable = true)
    private String retired;
    //связать

}