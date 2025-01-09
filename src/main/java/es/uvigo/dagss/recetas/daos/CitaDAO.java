package es.uvigo.dagss.recetas.daos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Cita;

public interface CitaDAO extends  JpaRepository<Cita, Long> {
    Cita findByFechaHora(Date hora);
    void deleteByFechaHora(Date hora);
    
}
