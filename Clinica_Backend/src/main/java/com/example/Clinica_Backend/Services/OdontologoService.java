package com.example.Clinica_Backend.Services;

import com.example.Clinica_Backend.Entities.Odontologo;
import com.example.Clinica_Backend.Repository.OdontologoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OdontologoService {
    private OdontologoRepository odontologoRepository;

    public void guardarOdontologo(Odontologo o) {
        odontologoRepository.save(o);
    }

    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);
    }

    public void modificarOdontologo(Odontologo o) {
        odontologoRepository.save(o);
    }

    public Optional<Odontologo> buscarOdontologo(Integer id) {
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }

    public Odontologo buscarMatricula(int matricula) {
        return odontologoRepository.findByMatricula(String.valueOf(matricula));
    }

}
