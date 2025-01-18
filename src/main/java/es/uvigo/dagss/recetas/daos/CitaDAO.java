package es.uvigo.dagss.recetas.daos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import java.util.List;


public interface CitaDAO extends JpaRepository<Cita, Long> {
    Cita findByFechaHora(Date hora);
    List<Cita> findByMedicoAtiende(Medico medico);
    void deleteByFechaHora(Date hora);
    List<Cita> findByPacienteAndEstadoCita(Paciente paciente, EstadoCita estado);
    
    // HU-P2: Método para encontrar citas planificadas por paciente
    List<Cita> findByPacienteCitadoAndEstadoCita(Paciente paciente, String estado);
}
