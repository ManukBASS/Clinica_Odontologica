package com.example.Clinica_Backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private int matricula;

    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY)
    /*@JsonBackReference(value = "odontologo_ref")*/
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

}
