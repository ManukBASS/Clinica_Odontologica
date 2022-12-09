package com.example.Clinica_Backend.Controllers;

import com.example.Clinica_Backend.Entities.Turno;
import com.example.Clinica_Backend.Entities.TurnoDTO;
import com.example.Clinica_Backend.Exceptions.RequestException;
import com.example.Clinica_Backend.Services.OdontologoService;
import com.example.Clinica_Backend.Services.PacienteService;
import com.example.Clinica_Backend.Services.TurnoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RequestMapping("/turnos")
@CrossOrigin(origins="*",exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
public class TurnoController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);
    TurnoService turnoService;
    OdontologoService odontologoService;
    PacienteService pacienteService;


    private Turno turnoMap(TurnoDTO turnoDTO){
        var paciente = pacienteService.buscarPaciente(turnoDTO.getIdPaciente());
        var odontologo = odontologoService.buscarOdontologo(turnoDTO.getIdOdontologo());
        var fechaTurno = turnoDTO.getFechaTurno();

        var turno = new Turno();
        turno.setPaciente(paciente.get());
        turno.setOdontologo(odontologo.get());
        turno.setFechaTurno(fechaTurno);

        turnoService.guardarTurno(turno);

        return turno;
    }

    @GetMapping("/listar")
    public List<Turno> listarTurno() {
        return turnoService.listarTurno();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarTurno(@PathVariable Integer id){

        if (turnoService.buscarTurno(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun turno"));
        }

        String response = "";
        ResponseEntity<?> status = null;

        if (turnoService.buscarTurno(id).isEmpty()){
            response = "\n"+"Id: "+ id + " no corresponde a ningun turno";
            status = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            turnoService.buscarTurno(id);
            status = ResponseEntity.ok(turnoService.buscarTurno(id));
        }
        return status;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarTurno(@RequestBody TurnoDTO turno){
        var turnos = turnoMap(turno);

        if (turno.getFechaTurno().equals("") ||  turno.getFechaTurno() == null){
            throw new RequestException("P-400","Fecha de Turno is required");
        }

        if (turno.getIdOdontologo().toString().equals("")){
            throw new RequestException("P-400","ID Odontologo is required");
        }

        if (turno.getIdPaciente().toString().equals("")){
            throw new RequestException("P-400","ID Paciente is required");
        }

        if (turno != null)
            turnoService.guardarTurno(turnos);
        return new ResponseEntity<>("El turno se agrego con exito", null, HttpStatus.CREATED);

    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@RequestBody Turno turno){

        var fechaTurno = turno.getFechaTurno();
        var paciente = turno.getPaciente();
        var odontologo = turno.getOdontologo();
        var id = turno.getId();

        if (fechaTurno.equals("") && paciente == null && odontologo == null){
            throw new RequestException("P-400", "No se registraron datos para actualizar");
        }

        if (fechaTurno.equals("")){
            throw new RequestException("P-400","Fecha de Turno is required");
        }

        if (odontologo == null){
            throw new RequestException("P-400","Odontologo is required");
        }

        if (paciente == null){
            throw new RequestException("P-400","Paciente is required");
        }

        if (turnoService.buscarTurno(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun turno"));
        }

        turnoService.modificarTurno(turno);
        return new ResponseEntity<>("Se modifico el turno con id: " + turno.getId(), null, HttpStatus.OK);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) {

        if (turnoService.buscarTurno(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun turno"));
        }

        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Se borro el turno con id: " + id, null, HttpStatus.OK);
    }

    @PatchMapping("/modificarFechaTurno/{id}")
    public ResponseEntity<String> modificarFechaTurno(@RequestBody Turno turno, @PathVariable Integer id){

        var fechaTurno = turno.getFechaTurno();

        if (fechaTurno.equals("")) {
            throw new RequestException("P-400","Fecha de Turno no puede ser nulo");
        }

        if (turnoService.buscarTurno(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun turno"));
        }

        turnoService.modificarFechaTurno(id, turno.getFechaTurno());
        return new ResponseEntity<>("Se modifico la fecha del turno con id: " + id, null, HttpStatus.OK);
    }
}
