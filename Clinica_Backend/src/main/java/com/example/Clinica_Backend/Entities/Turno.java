package com.example.Clinica_Backend.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fechaTurno;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    /*@JsonManagedReference(value = "paciente_ref")*/
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id")
    /*@JsonManagedReference(value = "odontologo_ref")*/
    private Odontologo odontologo;
}
