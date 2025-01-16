package es.uvigo.dagss.recetas.controladores;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.services.CentroSaludService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;



@RestController
@RequestMapping(path = "/api/centrosalud", produces = MediaType.APPLICATION_JSON_VALUE)
public class CentroSaludController {

    @Autowired
    private CentroSaludService centroSaludService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void crearCentroSalud(@RequestBody @Valid CentroSalud centroSalud) {
        // Lógica para crear un centro de salud
        this.centroSaludService.crearCentroSalud(centroSalud);
    }

    @PutMapping
    public CentroSalud actualizarCentroSalud(@RequestBody CentroSalud centroSalud) {
        // Lógica para actualizar un centro de salud
        if (this.centroSaludService.buscarPorId(centroSalud.getId()) != null){
            this.centroSaludService.actualizarCentroSalud(centroSalud);
            return centroSalud;
        }else{
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @GetMapping("/{nombre}")
    public List<CentroSalud> buscarCentroSalud(@PathVariable String nombre) {
        // Lógica para buscar un centro de salud por su ID
        return centroSaludService.buscarPorNombre(nombre);

    }

    @GetMapping(path = "/lista", consumes = MediaType.ALL_VALUE)
    public List<CentroSalud> buscarTodos() {
        // Lógica para buscar todos los centros de salud
        return centroSaludService.buscarTodos();
    }

    @GetMapping("/id/{id}")
    public CentroSalud buscarPorId(@PathVariable Long id) {
        // Lógica para buscar un centro de salud por su ID
        return centroSaludService.buscarPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public CentroSalud buscarPorNombre(@PathVariable String nombre) {
        // Lógica para buscar un centro de salud por su nombre
        return centroSaludService.buscarPorNombreExacto(nombre);
    }

    @GetMapping("/direccion/{direccion}")
    public List<CentroSalud> buscarPorDireccion(@PathVariable String direccion) {
        // Lógica para buscar un centro de salud por su dirección
        return centroSaludService.buscarPorDireccion(direccion);
    }

    @GetMapping("/telefono/{telefono}")
    public CentroSalud buscarPorTelefono(@PathVariable String telefono) {
        // Lógica para buscar un centro de salud por su teléfono
        return centroSaludService.buscarPorTelefono(telefono);

    }

    @GetMapping("/email/{email}")
    public CentroSalud buscarPorEmail(@PathVariable String email) {
        // Lógica para buscar un centro de salud por su email
        return centroSaludService.buscarPorEmail(email);
    }

    @DeleteMapping("/{id}")
    public void eliminarCentroSalud(@PathVariable Long id) {
        // Lógica para eliminar un centro de salud por su ID
        centroSaludService.eliminarCentroSalud(id);
    }

    @DeleteMapping("/nombre/{nombre}")
    public void eliminarCentroSalud(@PathVariable String nombre) {
        // Lógica para eliminar un centro de salud por su nombre
        centroSaludService.eliminarCentroSalud(nombre);
    }


}
