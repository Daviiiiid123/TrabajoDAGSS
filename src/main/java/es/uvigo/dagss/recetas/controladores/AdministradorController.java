package es.uvigo.dagss.recetas.controladores;

import java.net.URI;
import java.util.List;

import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.services.AdministradorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> listarAdministradores() {
        return administradorService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Administrador obtenerAdministrador(@PathVariable Long id) {
        Administrador administrador = administradorService.buscarPorId(id);
        if (administrador == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador not found");
        }
        return administrador;
        }

        @PostMapping
        
    public URI crearAdministrador(@RequestBody @Valid Administrador administrador) {     
        administradorService.crearAdministrador(administrador);
        return ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(administrador.getId())
            .toUri();
    }

    @PutMapping
    public void actualizarAdministrador(@RequestBody Administrador administrador) {
        if (administradorService.buscarPorId(administrador.getId()) != null){
            administradorService.actualizarAdministrador(administrador);
        }else{
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    // Cambiado para establecer activo a false en lugar de eliminar la entidad
    // HU-A2: Eliminación lógica estableciendo activo a false
    @DeleteMapping("/{id}")
    public void eliminarAdministrador(@PathVariable Long id) {
        Administrador administrador = administradorService.buscarPorId(id);
        administrador.setActivo(false);
        administradorService.actualizarAdministrador(administrador);
    }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
   }

   @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleNotFoundException(ResponseStatusException e) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado");
    }
}
