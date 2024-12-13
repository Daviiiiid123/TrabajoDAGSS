package es.uvigo.dagss.recetas.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Medico;

public interface MedicoDAO extends JpaRepository<Medico, Long> {
    Medico findByNumeroColegiado(String numeroColegiado);
    Medico findByDNI(String dni);
    List<Medico> findByActivoTrue();
    
}