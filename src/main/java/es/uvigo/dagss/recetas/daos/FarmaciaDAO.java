package es.uvigo.dagss.recetas.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.CentroSalud;
import es.uvigo.dagss.recetas.entidades.Farmacia;

public interface FarmaciaDAO extends JpaRepository<Farmacia, Long> {
    Farmacia findByNombre(String nombre);
    Farmacia findByDireccion(String direccion);
    Farmacia findByTelefono(String telefono);
    Farmacia findByEmail(String email);
    List<Farmacia> findByActivoTrue();
    List<CentroSalud> findByNombreContains(String nombre);
    List<CentroSalud> findByDireccionContains(String direccion);
}
