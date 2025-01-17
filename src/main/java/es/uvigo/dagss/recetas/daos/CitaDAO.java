package es.uvigo.dagss.recetas.daos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import java.util.List;

public interface CitaDAO extends  JpaRepository<Cita, Long> {
    Cita findByFechaHora(Date hora);
    List<Cita> findByMedico(Medico medico);
    void deleteByFechaHora(Date hora);
    
}
