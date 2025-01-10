package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CentroSaludDAO;
import es.uvigo.dagss.recetas.entidades.CentroSalud;

@Service
public class CentroSaludService {

    @Autowired
    private CentroSaludDAO centroSaludDAO;

    public CentroSaludService() {
    }

    public List<CentroSalud> buscarPorNombre(String nombre) {
        return centroSaludDAO.findByNombreContains(nombre);
    }

    public void crearCentroSalud(CentroSalud centroSalud) {
        centroSaludDAO.save(centroSalud);
    }

    // Cambiado para establecer activo a false en lugar de eliminar la entidad
    // HU-A3: Eliminación lógica estableciendo activo a false
    public void eliminarCentroSalud(CentroSalud centroSalud) {
        centroSalud.setActivo(false);
        centroSaludDAO.save(centroSalud);
    }

    public void actualizarCentroSalud(CentroSalud centroSalud) {
        centroSaludDAO.save(centroSalud);
    }

    public List<CentroSalud> buscarTodos() {
        return centroSaludDAO.findAll();
    }

    public CentroSalud buscarPorId(Long id) {
        return centroSaludDAO.findById(id).orElse(null);
    }

    public CentroSalud buscarPorNombreExacto(String nombre) {
        return centroSaludDAO.findByNombre(nombre);
    }

    public List<CentroSalud> buscarPorDireccion(String direccion) {
        return centroSaludDAO.findByDireccionContains(direccion);
    }

    public CentroSalud buscarPorTelefono(String telefono) {
        return centroSaludDAO.findByTelefono(telefono);
    }

    public CentroSalud buscarPorEmail(String email) {
        return centroSaludDAO.findByEmail(email);
    }

    public List<CentroSalud> buscarActivos() {
        return centroSaludDAO.findByActivoTrue();
    }

    // Cambiado para establecer activo a false en lugar de eliminar la entidad por ID
    // HU-A3: Eliminación lógica estableciendo activo a false
    public void eliminarCentroSalud(Long id) {
        CentroSalud centroSalud = centroSaludDAO.findById(id).orElse(null);
        if (centroSalud != null) {
            centroSalud.setActivo(false);
            centroSaludDAO.save(centroSalud);
        }
    }

    // Cambiado para establecer activo a false en lugar de eliminar la entidad por nombre
    // HU-A3: Eliminación lógica estableciendo activo a false
    public void eliminarCentroSalud(String nombre) {
        CentroSalud centroSalud = centroSaludDAO.findByNombre(nombre);
        if (centroSalud != null) {
            centroSalud.setActivo(false);
            centroSaludDAO.save(centroSalud);
        }
    }
}
