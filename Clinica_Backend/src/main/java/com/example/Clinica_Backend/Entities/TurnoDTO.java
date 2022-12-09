package com.example.Clinica_Backend.Entities;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TurnoDTO {

    private Integer idOdontologo;
    private Integer idPaciente;
    private String fechaTurno;
}
