package es.uvigo.dagss.recetas.controladores;

import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.services.PacienteService;

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

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void actualizarPaciente(Long id, Paciente paciente) {
        // Lógica para actualizar un paciente
        paciente.setId(id);
        this.pacienteService.updatePaciente(paciente);
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

   




}
