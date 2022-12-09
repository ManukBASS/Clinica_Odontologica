package com.example.Clinica_Backend.Services;

import com.example.Clinica_Backend.Entities.Turno;
import com.example.Clinica_Backend.Repository.TurnoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class TurnoService {
    private TurnoRepository turnoRepository;

    public void guardarTurno(Turno t) {
        turnoRepository.save(t);
    }

    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }

    public void modificarTurno(Turno t) {
        turnoRepository.save(t);
    }

    public Optional<Turno> buscarTurno(Integer id) {
        return turnoRepository.findById(id);
    }

    public List<Turno> listarTurno() {
        return turnoRepository.findAll();
    }

    public void modificarFechaTurno(Integer id, String turno){
        turnoRepository.cambiarFechaTurno(id, turno);
    }

}
