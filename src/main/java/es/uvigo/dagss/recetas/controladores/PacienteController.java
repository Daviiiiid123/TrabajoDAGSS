package es.uvigo.dagss.recetas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.services.CitaService;
import es.uvigo.dagss.recetas.services.PacienteService;
import es.uvigo.dagss.recetas.services.RecetaService;

@RestController
@RequestMapping(path = "/api/paciente", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private CitaService citaService;

    @Autowired
    private RecetaService recetaService;

    public PacienteController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crearPaciente(@RequestBody Paciente paciente) {
        // Lógica para crear un paciente
        this.pacienteService.createPaciente(paciente);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        // Lógica para actualizar un paciente
        paciente.setId(id);
        this.pacienteService.updatePaciente(paciente);
    }

    @DeleteMapping(path = "/{id}")
    public void borrarPaciente(@PathVariable Long id) {
        // Lógica para borrar un paciente
        this.pacienteService.deletePaciente(id);
    }

    @GetMapping(path = "/{id}")
    public Paciente buscarPaciente(@PathVariable Long id) {
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

    // HU-P1: Endpoint para obtener el "Home" de un paciente
    @GetMapping(path = "/home/{id}", consumes = MediaType.ALL_VALUE)
    public String obtenerHomePaciente(@PathVariable Long id) {
        return this.pacienteService.getPacienteHome(id);
    }

    // HU-P2: Endpoint para obtener las citas planificadas de un paciente
    @GetMapping(path = "/{id}/citas", consumes = MediaType.ALL_VALUE)
    public List<Cita> obtenerCitasPlanificadas(@PathVariable Long id) {
        return this.pacienteService.getCitasPlanificadas(id);
    }

    // HU-P3: Endpoint para crear una nueva cita para un paciente
    @PostMapping(path = "/{id}/citas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cita crearCita(@PathVariable Long id, @RequestBody Cita cita) {
        return this.citaService.crearCita(id, cita.getMedicoAtiende().getId(), cita.getFechaHora());
    }

    // HU-P2: Endpoint para anular una cita de un paciente
    @PutMapping(path = "/citas/anular/{citaId}", consumes = MediaType.ALL_VALUE)
    public void anularCitaPaciente(@PathVariable Long citaId) {
        this.citaService.anularCitaPaciente(citaId);
    }

    // HU-P4: Endpoint para obtener las recetas pendientes de un paciente
    @GetMapping(path = "/{id}/recetas", consumes = MediaType.ALL_VALUE)
    public List<Receta> obtenerRecetasPendientes(@PathVariable Long id) {
        Paciente paciente = this.pacienteService.findPacienteById(id).get();
        return this.recetaService.getRecetasPendientes(paciente);
    }

    // HU-P5: Endpoint para modificar las credenciales de acceso de un paciente
    @PutMapping(path = "/{id}/credenciales", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Paciente modificarCredenciales(@PathVariable Long id, @RequestBody String nuevoPassword) {
        return this.pacienteService.modificarCredenciales(id, nuevoPassword);
    }

    // HU-P5: Endpoint para actualizar los datos básicos de un paciente
    @PutMapping(path = "/{id}/datos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Paciente actualizarDatosBasicos(@PathVariable Long id, @RequestBody Paciente nuevosDatos) {
        return this.pacienteService.actualizarDatosBasicos(id, nuevosDatos);
    }
}
