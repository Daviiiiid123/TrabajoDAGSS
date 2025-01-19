package es.uvigo.dagss.recetas.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.FarmaciaDAO;
import es.uvigo.dagss.recetas.daos.RecetaDAO;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;

@Service

public class RecetaService {
    @Autowired
    private RecetaDAO recetaDAO;

    @Autowired
    private FarmaciaDAO farmaciaDAO;

    public RecetaService() {
    }

    public Receta crear(Receta receta) {
        return recetaDAO.save(receta);
    }

    public void eliminar(Long id) {
        recetaDAO.deleteById(id);
    }

    public Receta actualizar(Receta receta) {
        return recetaDAO.save(receta);
    }

    public List<Receta> listar() {
        return recetaDAO.findAll();
    }

    public Receta buscarPlanificada() {
        return recetaDAO.findByEstado(EstadoCita.PLANIFICADA);
    }


    public Receta buscarPorFarmaciaServidora(Farmacia farmacia) {
        return recetaDAO.findByFarmaciaServidora(farmacia);
    }

    // HU-P4: Método para obtener las recetas pendientes de un paciente
    public List<Receta> getRecetasPendientes(Paciente paciente) {
        return recetaDAO.findByPrescripcion_PacienteAndEstado(paciente, Receta.Estado.PLANIFICADA);
    }

    // HU-F2: Método para obtener las recetas planificadas de un paciente
    public List<Receta> getRecetasPlanificadas(Long pacienteId) {
        return recetaDAO.findByPrescripcion_PacienteIdAndEstado(pacienteId, Receta.Estado.PLANIFICADA);
    }

    // HU-F3: Método para anotar una receta como servida
    public Receta anotarRecetaServida(Long recetaId, Long farmaciaId) {
        Optional<Receta> receta = recetaDAO.findById(recetaId);
        if (receta.isPresent()) {
            Receta recetaExistente = receta.get();
            if (recetaExistente.getEstado() == Receta.Estado.PLANIFICADA) {
                recetaExistente.setEstado(Receta.Estado.SERVIDA);
                recetaExistente.setFarmaciaServidora(farmaciaDAO.findById(farmaciaId).orElse(null));
                return recetaDAO.save(recetaExistente);
            } else {
                throw new RuntimeException("La receta no está en estado PLANIFICADA");
            }
        } else {
            throw new RuntimeException("Receta no encontrada");
        }
    }

    public List<Receta> generarPlanRecetas(Prescripcion prescripcion) {
        List<Receta> planRecetas = new ArrayList<>();
        int dosisDiaria = prescripcion.getDosis();
        int dosisPorEnvase = prescripcion.getMedicamento().getDosis();
        // totalDias se calcula en base a la diferencia de tiempo entre la fecha de inicio y la fecha de fin y se divide por el número de milisegundos que tiene un día
        int totalDias = (int) ((prescripcion.getFechaFin().getTime() - prescripcion.getFechaInicio().getTime()) / 86400000);
        int totalEnvases = (int) Math.ceil((double) (dosisDiaria * totalDias) / dosisPorEnvase);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prescripcion.getFechaInicio());

        for (int i = 0; i < totalEnvases; i++) {
            Date fechaInicio = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, dosisPorEnvase / dosisDiaria);
            Date fechaFin = calendar.getTime();

            Receta receta = new Receta(prescripcion, fechaInicio, fechaFin, 1, Receta.Estado.PLANIFICADA);
            planRecetas.add(receta);
            recetaDAO.save(receta);
        }

        return planRecetas;
    }

    public List<Receta> getRecetasPorPrescripcion(Long prescripcionId) {
        return recetaDAO.findByPrescripcionId(prescripcionId);
    }

}
