package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;

public interface RecetaDAO extends JpaRepository<Receta, Long> {
    Receta findByEstado(EstadoCita estado);
    Receta findByFarmaciaServidora(Farmacia farmacia);

}
