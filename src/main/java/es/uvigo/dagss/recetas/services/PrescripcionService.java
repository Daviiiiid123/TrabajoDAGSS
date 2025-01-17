package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.PrescripcionDAO;
import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;

@Service

public class PrescripcionService {
    
    @Autowired
    private PrescripcionDAO prescripcionDAO;

    public PrescripcionService() {
    }

    public Prescripcion crear(Prescripcion prescripcion) {
        return prescripcionDAO.save(prescripcion);
    }

    public void eliminar(Long id) {
        prescripcionDAO.deleteById(id);
    }

    public Prescripcion actualizar(Prescripcion prescripcion) {
        return prescripcionDAO.save(prescripcion);
    }

    public Prescripcion buscarPorMedico(Medico medico) {
        return prescripcionDAO.findByMedico(medico);
    }

    public Prescripcion buscarPorPaciente(Paciente paciente) {
        return prescripcionDAO.findByPaciente(paciente);
    }

    public Prescripcion buscarPorMedicamento(Medicamento medicamento) {
        return prescripcionDAO.findByMedicamento(medicamento);
    }

    public Prescripcion buscarActiva() {
        return prescripcionDAO.findByActivaTrue();
    }
    public Prescripcion buscarPorId(long id){
        return prescripcionDAO.findById(id).orElse(null);
    }

    public List<Prescripcion> listarTodas() {
        return prescripcionDAO.findAll();
    }

    public Prescripcion obtenerPorId(Long id) {
        return prescripcionDAO.findById(id).orElse(null);
    }

    public Prescripcion actualizar(Long id, Prescripcion prescripcion) {
        if (prescripcionDAO.existsById(id)) {
            prescripcion.setId(id);
            return prescripcionDAO.save(prescripcion);
        } else {
            return null;
        }    
    }

}
