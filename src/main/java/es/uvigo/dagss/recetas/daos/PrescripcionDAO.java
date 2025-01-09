package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Prescripcion;

public interface PrescripcionDAO extends  JpaRepository<Prescripcion, Long> {
    Prescripcion findByMedico(String medico);
    Prescripcion findByPaciente(String paciente);
    Prescripcion findByMedicamento(String medicamento);
    Prescripcion findByActivaTrue();
    void deleteByMedico(String medico);
    void deleteByPaciente(String paciente);
    void deleteByMedicamento(String medicamento);
    void deleteByActivaTrue();
    
}
