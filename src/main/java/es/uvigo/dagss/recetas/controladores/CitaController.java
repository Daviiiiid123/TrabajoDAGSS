package es.uvigo.dagss.recetas.controladores;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.services.CitaService;
import es.uvigo.dagss.recetas.services.MedicoService;
import jakarta.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(path = "/api/citas", produces = "application/json")
public class CitaController {

    @Autowired
    private CitaService citaService;
    @Autowired
    private MedicoService medicoService;
    
    public CitaController() {
    }

    @PostMapping(consumes = "application/json")
    public URI crearCita(@RequestBody @Valid Cita cita) {
        // Lógica para crear una cita
        this.citaService.crear(cita);
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(cita.getId())
            .toUri();
    }

    @PutMapping
        public void actualizarCita(@RequestBody @Valid Cita cita) {
        // Lógica para actualizar una cita
        if( this.citaService.buscarPorId(cita.getId()) != null ){
            this.citaService.actualizar(cita);
        }else{
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @DeleteMapping(path = "/{id}")
    public void eliminarCita(@PathVariable Long id) {
        // Lógica para eliminar una cita
        this.citaService.eliminar(id);
    }

    @GetMapping(path = "/{id}", consumes = "application/json")
    public void buscarCita(@PathVariable Long id) {
        // Lógica para buscar una cita por su ID
        this.citaService.buscarPorId(id);
    }

    @GetMapping(path = "/lista")
    public List<Cita> buscarTodos() {
        // Lógica para buscar todas las citas
        return this.citaService.listarTodas();
    }
    
    @GetMapping(path = "/medico/{id}")
    public List<Cita> buscarPorIdMedico(@PathVariable Long id){
        return this.citaService.buscarPorMedico(this.medicoService.buscarPorId(id));
    }

    // HU-A7: Añadir endpoint para anular citas
    @PutMapping(path = "/anular/{id}")
    public void anularCita(@PathVariable Long id) {
        this.citaService.anularCita(id);
    }
    
    @GetMapping(path = "/citas_disponibles")
    public List<String> citasDisponibles(@RequestBody Medico medico, @RequestBody Date dia){
        return citaService.HuecosVaciosMedicoEnDia(medico, dia);
    }
}
