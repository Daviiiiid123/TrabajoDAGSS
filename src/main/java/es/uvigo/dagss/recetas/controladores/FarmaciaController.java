package es.uvigo.dagss.recetas.controladores;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.services.FarmaciaService;
import es.uvigo.dagss.recetas.services.RecetaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/farmacias", produces = MediaType.APPLICATION_JSON_VALUE)

public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private RecetaService recetaService;

    public FarmaciaController() {
    }

    @PostMapping
    public URI crearFarmacia(@RequestBody @Valid Farmacia farmacia) {
        // Lógica para crear una farmacia
        this.farmaciaService.crearFarmacia(farmacia);
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(farmacia.getId())
            .toUri();
    }

    @PutMapping
    public void actualizarFarmacia(@RequestBody @Valid Farmacia farmacia) {
        // Lógica para actualizar una farmacia
        if ( this.farmaciaService.buscarPorId(farmacia.getId()) != null ) {
            this.farmaciaService.actualizarFarmacia(farmacia);
        }else{
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @GetMapping("/{nombre}")
    public Farmacia buscarFarmacia(@PathVariable String nombre) {
        // Lógica para buscar una farmacia por su nombre
        return farmaciaService.buscarPorNombreExacto(nombre);

    }

    @GetMapping(path = "/lista")
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

    // HU-A6: Añadir endpoint para obtener farmacias activas
    @GetMapping(path = "/activos")
    public List<Farmacia> buscarFarmaciasActivas() {
        return this.farmaciaService.findActiveFarmacias();
    }

    // HU-F1: Endpoint para obtener el "Home" de una farmacia
    @GetMapping(path = "/home/{id}", consumes = MediaType.ALL_VALUE)
    public String obtenerHomeFarmacia(@PathVariable Long id) {
        return this.farmaciaService.getFarmaciaHome(id);
    }

    // HU-F2: Endpoint para obtener las recetas planificadas de un paciente.
    // ESTO NO VA AQUÍ
    /*@GetMapping(path = "/recetas/{pacienteId}", consumes = MediaType.ALL_VALUE)
    public List<Receta> obtenerRecetasPlanificadas(@PathVariable Long pacienteId) {
        return this.recetaService.getRecetasPlanificadas(pacienteId);
    }*/

    // HU-F3: Endpoint para anotar una receta como servida
    @PutMapping(path = "/recetas/servir/{recetaId}/{farmaciaId}", consumes = MediaType.ALL_VALUE)
    public Receta anotarRecetaServida(@PathVariable Long recetaId, @PathVariable Long farmaciaId) {
        return this.recetaService.anotarRecetaServida(recetaId, farmaciaId);
    }

    // HU-F4: Endpoint para modificar las credenciales de acceso de una farmacia
    @PutMapping(path = "/{id}/credenciales", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Farmacia modificarCredenciales(@PathVariable Long id, @RequestBody String nuevoPassword) {
        return this.farmaciaService.modificarCredenciales(id, nuevoPassword);
    }

    // HU-F4: Endpoint para actualizar los datos básicos de una farmacia
    @PutMapping(path = "/{id}/datos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Farmacia actualizarDatosBasicos(@PathVariable Long id, @RequestBody Farmacia nuevosDatos) {
        return this.farmaciaService.actualizarDatosBasicos(id, nuevosDatos);
    }

    

}
