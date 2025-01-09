package es.uvigo.dagss.recetas.controladores;


import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.services.PrescripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;




@RestController
@RequestMapping("/api/prescripcion")
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
    @ResponseStatus(HttpStatus.CREATED)
    public Prescripcion crear(@RequestBody Prescripcion prescripcion) {
        return prescripcionService.crear(prescripcion);
    }

    @PutMapping("/{id}")
    public Prescripcion actualizar(@PathVariable Long id, @RequestBody Prescripcion prescripcion) {
        Prescripcion prescripcionActualizada = prescripcionService.actualizar(id, prescripcion);
        if (prescripcionActualizada != null) {
            return prescripcionActualizada;
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
