package es.uvigo.dagss.recetas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.services.PacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/paciente", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    public PacienteController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crearPaciente(Paciente paciente) {
        // Lógica para crear un paciente
        this.pacienteService.createPaciente(paciente);
    }

    @PutMapping
    public void actualizarPaciente(@RequestBody @Valid Paciente paciente) {
        // Lógica para actualizar una paciente
        if( this.pacienteService.buscarPorId(paciente.getId()) != null ){
            this.pacienteService.updatePaciente(paciente);
        }else{
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public void borrarPaciente(Long id) {
        // Lógica para borrar un paciente
        this.pacienteService.deletePaciente(id);
    }

    @GetMapping(path = "/{id}")
    public Paciente buscarPaciente(Long id) {
        // Lógica para buscar un paciente por su ID
        return this.pacienteService.findPacienteById(id).get();
    }

    @GetMapping(path = "/lista", consumes = MediaType.ALL_VALUE)
    public List<Paciente> buscarTodos() {
        // Lógica para buscar todos los pacientes
        return this.pacienteService.findAllPacientes();
    }

    // HU-A5: Añadir endpoint para obtener pacientes activos
    @GetMapping(path = "/activos", consumes = MediaType.ALL_VALUE)
    public List<Paciente> buscarPacientesActivos() {
        return this.pacienteService.findActivePacientes();
    }
}
