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

import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.services.MedicamentoService;

@RestController
@RequestMapping(path = "/api/medicamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Medicamento crearMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoService.crearMedicamento(medicamento);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Medicamento actualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento medicamento) {
        medicamento.setId(id);
        return medicamentoService.actualizarMedicamento(medicamento);
    }

    @DeleteMapping(path = "/{id}")
    public void eliminarMedicamento(@PathVariable Long id) {
        medicamentoService.eliminarMedicamento(id);
    }

    @GetMapping(path = "/{id}")
    public Medicamento buscarMedicamento(@PathVariable Long id) {
        return medicamentoService.buscarPorId(id);
    }

    @GetMapping(path = "/lista", consumes = MediaType.ALL_VALUE)
    public List<Medicamento> buscarTodos() {
        return medicamentoService.buscarTodos();
    }

    @GetMapping(path = "/activos", consumes = MediaType.ALL_VALUE)
    public List<Medicamento> buscarMedicamentosActivos() {
        return medicamentoService.buscarActivos();
    }
}
