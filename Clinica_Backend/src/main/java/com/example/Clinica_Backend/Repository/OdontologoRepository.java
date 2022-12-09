package com.example.Clinica_Backend.Repository;

import com.example.Clinica_Backend.Entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {

    @Query("select o FROM Odontologo o where o.matricula= ?1")
    public Odontologo findByMatricula(String matricula);
}
