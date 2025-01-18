package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Cita;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Paciente;

public interface PacienteDAO extends JpaRepository<Paciente, Long> {
    Paciente findByNSS(String nss);
    Paciente findByDNI(String dni);
    Paciente findByEmail(String email);
    List<Paciente> findByActivoTrue();
}
