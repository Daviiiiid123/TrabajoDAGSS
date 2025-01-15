package es.uvigo.dagss.recetas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.services.MedicoService;

@RestController
@RequestMapping(path = "/api/medico", produces = "application/json")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;
    
    public MedicoController() {
    }
    @PostMapping(consumes = "application/json")
    public void crearMedico(Medico medico) {
        // Lógica para crear un médico
        this.medicoService.crearMedico(medico);
    }

    @DeleteMapping(path = "/{id}")
    public void eliminarMedico(Long id) {
        // Lógica para eliminar un médico
        this.medicoService.eliminarMedico(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public void actualizarMedico(Long id, Medico medico) {
        // Lógica para actualizar un médico
        medico.setId(id);
        this.medicoService.actualizarMedico(medico);
    }

    @GetMapping(path = "/{id}")
    public Medico buscarMedico(Long id) {
        // Lógica para buscar un médico por su ID
        return this.medicoService.buscarPorId(id);
    }

    @GetMapping(path = "/lista")
    public List<Medico> buscarTodos() {
        // Lógica para buscar todos los médicos
        return this.medicoService.buscarTodos();
    }

    @GetMapping(path = "/nombre/{nombre}")
    public Medico buscarPorNombre(String nombre) {
        // Lógica para buscar un médico por su nombre
        return this.medicoService.buscarPorNombreExacto(nombre);
    }

    @GetMapping(path = "/telefono/{telefono}")
    public Medico buscarPorTelefono(String telefono) {
        // Lógica para buscar un médico por su teléfono
        return this.medicoService.buscarPorTelefono(telefono);
    }
}