package es.uvigo.dagss.recetas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.services.CitaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/cita", produces = "application/json")
public class CitaController {

    @Autowired
    private CitaService citaService;
    
    public CitaController() {
    }

    @PostMapping(consumes = "application/json")
    public void crearCita(@RequestBody @Valid Cita cita) {
        // Lógica para crear una cita
        this.citaService.crear(cita);
    }

    @PostMapping(path = "/{id}", consumes = "application/json")
    public void actualizarCita(@RequestBody @Valid Cita cita) {
        // Lógica para actualizar una cita
      
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


}
