package es.uvigo.dagss.recetas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.services.AdministradorService;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> listarAdministradores() {
        return administradorService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Administrador obtenerAdministrador(@PathVariable Long id) {
        return administradorService.buscarPorId(id);
    }

    @PostMapping
    public void crearAdministrador(@RequestBody Administrador administrador) {
        administradorService.crearAdministrador(administrador);
    }

    @PutMapping("/{id}")
    public void actualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        administrador.setId(id); 
        administradorService.actualizarAdministrador(administrador);
    }

    // Cambiado para establecer activo a false en lugar de eliminar la entidad
    // HU-A2: Eliminación lógica estableciendo activo a false
    @DeleteMapping("/{id}")
    public void eliminarAdministrador(@PathVariable Long id) {
        Administrador administrador = administradorService.buscarPorId(id);
        administrador.setActivo(false);
        administradorService.actualizarAdministrador(administrador);
    }
}
