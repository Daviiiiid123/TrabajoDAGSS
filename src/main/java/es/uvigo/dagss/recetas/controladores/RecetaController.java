package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public List<Receta> listarRecetas() {
        return recetaService.listar();
    }


    @DeleteMapping("/{id}")
    public void eliminarReceta(@PathVariable Long id) {
        recetaService.eliminar(id);
    }
}
