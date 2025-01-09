package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Cita;

public interface CitaDAO extends  JpaRepository<Cita, Long> {
    Cita findByFecha(String fecha);
    Cita findByHora(String hora);
    Cita findByPaciente(String paciente);
    Cita findByMedico(String medico);
    Cita findByActivaTrue();
    void deleteByFecha(String fecha);
    void deleteByHora(String hora);
    void deleteByPaciente(String paciente);
    void deleteByMedico(String medico);
    void deleteByActivaTrue();
    
}
