package com.example.Clinica_Backend.Controllers;

import com.example.Clinica_Backend.Entities.Paciente;
import com.example.Clinica_Backend.Exceptions.RequestException;
import com.example.Clinica_Backend.Services.PacienteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/pacientes")
@CrossOrigin(origins="*",exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
public class PacienteController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);
    PacienteService pacienteService;

    @GetMapping("/listar")
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPaciente();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPacientes(@PathVariable Integer id) {

        if (pacienteService.buscarPaciente(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun paciente"));
        }

        String response = "";
        ResponseEntity<?> status = null;

        if (pacienteService.buscarPaciente(id).isEmpty()){
            response = "\n"+"Id: "+ id + " no corresponde a ningun paciente";
            status = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            pacienteService.buscarPaciente(id);
            status = ResponseEntity.ok(pacienteService.buscarPaciente(id));
        }
        return status;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarPacientes(@RequestBody Paciente paciente){

        if (paciente.getNombre().equals("") ||  paciente.getNombre() == null){
            throw new RequestException("P-400","Nombre is required");
        }

        if (paciente.getApellido().equals("") ||  paciente.getApellido() == null){
            throw new RequestException("P-400","Apellido is required");
        }

        if (paciente.getDomicilio().equals("") ||  paciente.getDomicilio() == null){
            throw new RequestException("P-400","Domicilio is required");
        }

        if (paciente.getDni().equals("") ||  paciente.getDni() == null){
            throw new RequestException("P-400","DNI is required");
        }

        if (paciente.getFechaAlta().equals("") ||  paciente.getFechaAlta() == null){
            throw new RequestException("P-400","Fecha de Alta is required");
        }

        if (paciente != null)
            pacienteService.guardarPaciente(paciente);
        return new ResponseEntity<>("El paciente se agrego con exito", null, HttpStatus.CREATED);

    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarPaciente(@RequestBody Paciente paciente){

        var nombre = paciente.getNombre();
        var apellido = paciente.getApellido();
        var domicilio = paciente.getDomicilio();
        var dni = paciente.getDni();
        var fechaAlta = paciente.getFechaAlta();
        var id = paciente.getId();

        if (nombre.equals("") && apellido.equals("") && domicilio.equals("") && dni.equals("") && fechaAlta.equals("")){
            throw new RequestException("P-400", "No se registraron datos para actualizar");
        }

        if (nombre.equals("")){
            throw new RequestException("P-400","Nombre is required");
        }

        if (apellido.equals("")){
            throw new RequestException("P-400","Apellido is required");
        }

        if (domicilio.equals("")){
            throw new RequestException("P-400","Domicilio is required");
        }

        if (dni.equals("")){
            throw new RequestException("P-400", "DNI is required");
        }

        if (fechaAlta.equals("")){
            throw new RequestException("P-400", "Fecha de Alta is required");
        }

        if (pacienteService.buscarPaciente(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun paciente"));
        }

        pacienteService.modificarPaciente(paciente);
        return new ResponseEntity<>("Se modifico el paciente con id: " + paciente.getId(), null, HttpStatus.OK);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
        if (pacienteService.buscarPaciente(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun paciente"));
        }
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Se borro el paciente con id: " + id, null, HttpStatus.OK);
    }

    @PatchMapping("modificarPaciente/{id}")
    public ResponseEntity<String> modificar(@RequestBody Paciente p,@PathVariable Integer id) {

        if (pacienteService.buscarPaciente(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun paciente"));
        }

        pacienteService.modificarPaciente(p, id);
        return new ResponseEntity<>("Se ha modificado con id: " + id, null, HttpStatus.OK);
    }

}
