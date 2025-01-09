package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;

public interface RecetaDAO extends JpaRepository<Receta, Long> {
    Receta findByNombre(String nombre);
    Receta findByActivoTrue();
    void deleteByNombre(String nombre);
    Receta findByFarmaciaServidora(Farmacia farmacia);

}
