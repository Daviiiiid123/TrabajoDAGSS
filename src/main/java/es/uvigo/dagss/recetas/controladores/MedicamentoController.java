package es.uvigo.dagss.recetas.controladores;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.services.MedicamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/medicamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping
    public URI crearMedicamento(@RequestBody @Valid Medicamento medicamento) {
        medicamentoService.crearMedicamento(medicamento);
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(medicamento.getId())
            .toUri();
    }

    @PutMapping(path = "/{id}")
    public void actualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        if (medicamentoService.buscarPorId(id) == null) {
            medicamento.setId(id);
        }
        medicamentoService.actualizarMedicamento(medicamento);
    }

    @DeleteMapping(path = "/{id}")
    public void eliminarMedicamento(@PathVariable Long id) {
        medicamentoService.eliminarMedicamento(id);
    }

    @GetMapping(path = "/{id}")
    public Medicamento buscarMedicamento(@PathVariable Long id) {
        return medicamentoService.buscarPorId(id);
    }

    @GetMapping(path = "/lista")
    public List<Medicamento> buscarTodos() {
        return medicamentoService.buscarTodos();
    }

    @GetMapping(path = "/activos")
    public List<Medicamento> buscarMedicamentosActivos() {
        return medicamentoService.buscarActivos();
    }

    // Endpoint para buscar medicamentos por nombre comercial
    @GetMapping(path = "/buscarPorNombreComercial/{nombreComercial}")
    public List<Medicamento> buscarPorNombreComercial(@PathVariable String nombreComercial) {
        return medicamentoService.buscarPorNombreComercial(nombreComercial);
    }

    // Endpoint para buscar medicamentos por principio activo
    @GetMapping(path = "/buscarPorPrincipioActivo/{principioActivo}")
    public List<Medicamento> buscarPorPrincipioActivo(@PathVariable String principioActivo) {
        return medicamentoService.buscarPorPrincipioActivo(principioActivo);
    }

    // Endpoint para buscar medicamentos por fabricante
    @GetMapping(path = "/buscarPorFabricante/{fabricante}")
    public List<Medicamento> buscarPorFabricante(@PathVariable String fabricante) {
        return medicamentoService.buscarPorFabricante(fabricante);
    }

    // Endpoint para buscar medicamentos por familia
    @GetMapping(path = "/buscarPorFamilia/{familia}")
    public List<Medicamento> buscarPorFamilia(@PathVariable String familia) {
        return medicamentoService.buscarPorFamilia(familia);
    }
}
