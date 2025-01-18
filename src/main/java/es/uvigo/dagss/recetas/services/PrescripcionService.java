package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.PrescripcionDAO;
import es.uvigo.dagss.recetas.daos.RecetaDAO;
import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;

@Service

public class PrescripcionService {
    
    @Autowired
    private PrescripcionDAO prescripcionDAO;

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private RecetaDAO recetaDAO;

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

    public Prescripcion crearPrescripcion(Prescripcion prescripcion) {
        Prescripcion nuevaPrescripcion = prescripcionDAO.save(prescripcion);
        recetaService.generarPlanRecetas(nuevaPrescripcion);
        return nuevaPrescripcion;
    }

    public void anularPrescripcion(Long prescripcionId) {
        Optional<Prescripcion> prescripcionOpt = prescripcionDAO.findById(prescripcionId);
        if (prescripcionOpt.isPresent()) {
            Prescripcion prescripcion = prescripcionOpt.get();
            prescripcion.setActiva(false);
            prescripcionDAO.save(prescripcion);

            List<Receta> recetas = recetaService.getRecetasPorPrescripcion(prescripcionId);
            for (Receta receta : recetas) {
                receta.setEstado(Receta.Estado.ANULADA);
                recetaDAO.save(receta);
            }
        } else {
            throw new RuntimeException("Prescripción no encontrada");
        }
    }

}
