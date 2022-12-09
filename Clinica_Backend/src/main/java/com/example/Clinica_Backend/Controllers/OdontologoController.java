package com.example.Clinica_Backend.Controllers;

import com.example.Clinica_Backend.Entities.Odontologo;
import com.example.Clinica_Backend.Exceptions.RequestException;
import com.example.Clinica_Backend.Services.OdontologoService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

@AllArgsConstructor
@RequestMapping("/odontologos")
@CrossOrigin(origins="*",exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
public class OdontologoController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);
    OdontologoService odontologoService;

    @GetMapping("/listar")
    public List<Odontologo> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarOdontologos(@PathVariable Integer id) {

        if (odontologoService.buscarOdontologo(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun odontólogo"));
        }

        String response = "";
        ResponseEntity<?> status = null;

        if (odontologoService.buscarOdontologo(id).isEmpty()){
            response = "\n"+"Id: "+ id + " no corresponde a ningun odontólogo";
            status = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        else {
            odontologoService.buscarOdontologo(id);
            status = ResponseEntity.ok(odontologoService.buscarOdontologo(id));
        }
        return status;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarOdontologos(@RequestBody Odontologo odontologo) {

        if (odontologo.getNombre().equals("") && odontologo.getApellido().equals("") && odontologo.getMatricula() == 0) {
            throw new RequestException("P-400", "No se registraron datos para guardar");
        }

        if (odontologo.getNombre().equals("") ||  odontologo.getNombre() == null){
            throw new RequestException("P-400","Nombre is required");
        }

        if (odontologo.getApellido().equals("") ||  odontologo.getApellido() == null){
            throw new RequestException("P-400","Apellido is required");
        }

        if (odontologo.getMatricula() == 0){
            throw new RequestException("P-400","Matricula is required");
        }

        if (odontologoService.buscarMatricula(odontologo.getMatricula()) != null){
            throw new RequestException("P-400", "Matricula ya existente");
        }

        odontologoService.guardarOdontologo(odontologo);
        return new ResponseEntity<>("El odontologo se guardo con exito", null, HttpStatus.CREATED);

    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologos(@RequestBody Odontologo odontologo){

        var nombre = odontologo.getNombre();
        var apellido = odontologo.getApellido();
        var matricula = odontologo.getMatricula();
        var id = odontologo.getId();


        if (nombre.equals("") && apellido.equals("") && matricula == 0){
                throw new RequestException("P-400", "No se registraron datos para actualizar");
        }

        if (nombre.equals("")){
                throw new RequestException("P-400","Nombre is required");
        }

        if (apellido.equals("")){
            throw new RequestException("P-400","Apellido is required");
        }

        if (matricula == 0){
                throw new RequestException("P-400","Matricula is required");
        }

        if (odontologoService.buscarMatricula(matricula) != null){
                throw new RequestException("P-400", "Matricula ya existente");
        }

        if (odontologoService.buscarOdontologo(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun odontólogo"));
        }

        odontologoService.modificarOdontologo(odontologo);
        return new ResponseEntity<>("Se modifico el odontologo con id: " + odontologo.getId(), null, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) {
        if (odontologoService.buscarOdontologo(id).isEmpty()) {
            throw new RequestException("P-400", ("El id " + id + " no corresponde a ningun odontólogo"));
        }
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Se borro el odontologo con id: " + id, null, HttpStatus.OK);
    }

}
