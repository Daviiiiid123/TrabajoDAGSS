package es.uvigo.dagss.recetas.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.CentroSalud;

public interface CentroSaludDAO extends JpaRepository<CentroSalud, Long> {

    CentroSalud findByNombre(String nombre);
    CentroSalud findByDireccion(String direccion);
    CentroSalud findByTelefono(String telefono);
    CentroSalud findByEmail(String email);
    List<CentroSalud> findByActivoTrue(); // Añadido para encontrar entidades CentroSalud activas
    List<CentroSalud> findByNombreContains(String nombre);
    List<CentroSalud> findByDireccionContains(String direccion);
    void deleteByNombre(String nombre);

}
