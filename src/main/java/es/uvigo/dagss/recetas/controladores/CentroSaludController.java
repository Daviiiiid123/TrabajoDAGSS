package es.uvigo.dagss.recetas.controladores;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.services.CentroSaludService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;



@RestController
@RequestMapping(path = "/api/centrosalud", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CentroSaludController {

    @Autowired
    private CentroSaludService centroSaludService;

    @PostMapping
    public CentroSalud crearCentroSalud(@RequestBody @Valid CentroSalud centroSalud) {
        // Lógica para crear un centro de salud
        this.centroSaludService.crearCentroSalud(centroSalud);
        return null;
    }

    @GetMapping("/{nombre}")
    public List<CentroSalud> buscarCentroSalud(@PathVariable String nombre) {
        // Lógica para buscar un centro de salud por su ID
        return centroSaludService.buscarPorNombre(nombre);

    }
}
