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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.services.PrescripcionService;
import jakarta.validation.Valid;




@RestController
@RequestMapping(path = "/api/prescripciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescripcionController {

    @Autowired
    private PrescripcionService prescripcionService;

    @GetMapping
    public List<Prescripcion> listarTodas() {
        return prescripcionService.listarTodas();
    }

    @GetMapping("/{id}")
    public Prescripcion obtenerPorId(@PathVariable Long id) {
        Prescripcion prescripcion = prescripcionService.obtenerPorId(id);
        if (prescripcion != null) {
            return prescripcion;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescripción no encontrada");
        }
    }

    @PostMapping
    public URI crear(@RequestBody @Valid Prescripcion prescripcion) {
        Prescripcion nuevaPrescripcion = prescripcionService.crearPrescripcion(prescripcion);
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(nuevaPrescripcion.getId())
            .toUri();
    }

    @PutMapping
    public Prescripcion actualizar(@RequestBody Prescripcion prescripcion) {
        if( this.prescripcionService.buscarPorId(prescripcion.getId()) != null){
            return this.prescripcionService.actualizar(prescripcion);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prescripción no encontrada");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        prescripcionService.eliminar(id);
    }
}
