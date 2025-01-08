package es.uvigo.dagss.recetas.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Medico;

public interface MedicoDAO extends JpaRepository<Medico, Long> {
    Medico findByNumeroColegiado(String numeroColegiado);
    Medico findByDNI(String dni);
    List<Medico> findByActivoTrue();
    void deleteByNombre(String nombre);
    Medico findByTelefono(String telefono);
    Medico findByNombre(String nombre);
    Medico findByEmail(String email);
    List<Medico> findByNombreContains(String nombre);
    
}