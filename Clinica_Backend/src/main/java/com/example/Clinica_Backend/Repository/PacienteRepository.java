package com.example.Clinica_Backend.Repository;

import com.example.Clinica_Backend.Entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("select o FROM Paciente o where o.dni= ?1")
    public Paciente findByDni(int dni);

    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.nombre = ?2 WHERE p.id = ?1")
    Paciente cambiarNombre(Integer id, String nombre);

}
