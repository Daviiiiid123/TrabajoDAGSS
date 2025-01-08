package es.uvigo.dagss.recetas.controladores;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.services.PacienteService;

@RestController
@RequestMapping(path = "/api/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
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




}
