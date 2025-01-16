package es.uvigo.dagss.recetas.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;

public interface PrescripcionDAO extends  JpaRepository<Prescripcion, Long> {
    Prescripcion findByMedico(Medico medico);
    Prescripcion findByPaciente(Paciente paciente);
    Prescripcion findByMedicamento(Medicamento medicamento);
    Prescripcion findByActivaTrue();
    void findById();
    void deleteByMedico(Medico medico);
    void deleteByPaciente(Paciente paciente);
    void deleteByMedicamento(Medicamento medicamento);
    void deleteByActivaTrue();
    
}
