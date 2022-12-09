package com.example.Clinica_Backend.Repository;

import com.example.Clinica_Backend.Entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Turno t SET t.fechaTurno = ?2 WHERE t.id = ?1")
    int cambiarFechaTurno(Integer id, String turno);

    @Modifying
    @Transactional
    @Query("DELETE Turno t WHERE t.id = ?1")
    int borrarTurno(Integer id);
}
