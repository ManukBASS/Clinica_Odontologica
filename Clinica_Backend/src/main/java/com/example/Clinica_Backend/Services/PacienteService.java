package com.example.Clinica_Backend.Services;

import com.example.Clinica_Backend.Entities.Paciente;
import com.example.Clinica_Backend.Repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public void guardarPaciente(Paciente p) {
        pacienteRepository.save(p);
    }

    public void eliminarPaciente(Integer id) {
        pacienteRepository.deleteById(id);
    }

    public void modificarPaciente(Paciente p) {
        pacienteRepository.save(p);
    }

    public Optional<Paciente> buscarPaciente(Integer id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> listarPaciente() {
        return pacienteRepository.findAll();
    }

    public void modificarPaciente(Paciente p, Integer id) {
        var pacienteNew = pacienteRepository.findById(id).get();
        if(p.getNombre() != null) pacienteNew.setNombre(p.getNombre());
        if(p.getApellido() != null) pacienteNew.setApellido(p.getApellido());
        if(p.getDomicilio() != null) pacienteNew.setDomicilio(p.getDomicilio());
        if(p.getDni() != null) pacienteNew.setDni(p.getDni());
        if(p.getFechaAlta() != null) pacienteNew.setFechaAlta(p.getFechaAlta());
        pacienteRepository.save(pacienteNew);
    }

    public Paciente buscarDNI(int dni) {
        return pacienteRepository.findByDni(dni);
    }

}
