package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.AdministradorDAO;
import es.uvigo.dagss.recetas.entidades.Administrador;

@Service
public class AdministradorService {
    @Autowired
    public AdministradorDAO administradorDAO;

    public AdministradorService() {
    }

    public void crearAdministrador(Administrador administrador) {
        administradorDAO.save(administrador);
        
    }

    // Cambiado para establecer activo a false en lugar de eliminar la entidad
    // HU-A2: Eliminación lógica estableciendo activo a false
    public void eliminarAdministrador(Administrador administrador) {
        administrador.setActivo(false);
        administradorDAO.save(administrador);
    }

    public void actualizarAdministrador(Administrador administrador) {
        administradorDAO.save(administrador);
    }

    public List<Administrador> buscarTodos() {
        return administradorDAO.findAll();
    }

    public Administrador buscarPorId(Long id) {
        return administradorDAO.findById(id).orElse(null);
    }
}
