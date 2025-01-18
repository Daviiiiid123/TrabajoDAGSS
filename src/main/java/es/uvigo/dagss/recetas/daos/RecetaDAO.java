package es.uvigo.dagss.recetas.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Receta;

public interface RecetaDAO extends JpaRepository<Receta, Long> {
    Receta findByEstado(EstadoCita estado);
    Receta findByFarmaciaServidora(Farmacia farmacia);

    // HU-P4: Método para encontrar recetas planificadas por paciente
    List<Receta> findByPacienteAndEstado(Paciente paciente, Receta.Estado estado);

    // HU-F2: Método para encontrar recetas planificadas por paciente
    List<Receta> findByPacienteAndEstado(Long pacienteId, Receta.Estado estado);

    List<Receta> findByPrescripcionId(Long prescripcionId);
}
