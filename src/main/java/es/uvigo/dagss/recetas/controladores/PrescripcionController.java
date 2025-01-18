package es.uvigo.dagss.recetas.controladores;


import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.services.PrescripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




@RestController
@RequestMapping("/api/prescripciones")
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
    public URI crear(@RequestBody Prescripcion prescripcion) {
        prescripcionService.crear(prescripcion);
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(prescripcion.getId())
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
