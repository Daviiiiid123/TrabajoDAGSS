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

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.services.FarmaciaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/farmacia", produces = MediaType.APPLICATION_JSON_VALUE)

public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    public FarmaciaController() {
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crearFarmacia(@RequestBody @Valid Farmacia farmacia) {
        // Lógica para crear una farmacia
        this.farmaciaService.crearFarmacia(farmacia);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void actualizarFarmacia(@PathVariable Long id, @RequestBody @Valid Farmacia farmacia) {
        // Lógica para actualizar una farmacia
        farmacia.setId(id);
        this.farmaciaService.actualizarFarmacia(farmacia);
    }

    @GetMapping("/{nombre}")
    public Farmacia buscarFarmacia(@PathVariable String nombre) {
        // Lógica para buscar una farmacia por su nombre
        return farmaciaService.buscarPorNombreExacto(nombre);

    }

    @GetMapping(path = "/lista", consumes = MediaType.ALL_VALUE)
    public List<Farmacia> buscarTodos() {
        // Lógica para buscar todas las farmacias
        return farmaciaService.buscarTodos();
    }

    @GetMapping("/id/{id}")
    public Farmacia buscarPorId(@PathVariable Long id) {
        // Lógica para buscar una farmacia por su ID
        return farmaciaService.buscarPorId(id);
    }

    @GetMapping("/telefono/{telefono}")
    public Farmacia buscarPorTelefono(@PathVariable String telefono) {
        // Lógica para buscar una farmacia por su telefono
        return farmaciaService.buscarPorTelefono(telefono);
    }

    @GetMapping("/email/{email}")
    public Farmacia buscarPorEmail(@PathVariable String email) {
        // Lógica para buscar una farmacia por su email
        return farmaciaService.buscarPorEmail(email);
    }

    @GetMapping("/numeroColegiado/{numeroColegiado}")
    public Farmacia buscarPorNumeroColegiado(@PathVariable String numeroColegiado) {
        // Lógica para buscar una farmacia por su numeroColegiado
        return farmaciaService.buscarPorNumeroColegiado(numeroColegiado);
    }

    @DeleteMapping("/{id}")
    public void eliminarFarmacia(@PathVariable Long id) {
        // Lógica para eliminar una farmacia
        farmaciaService.eliminarFarmaciaPorId(id);
    }

    

}
