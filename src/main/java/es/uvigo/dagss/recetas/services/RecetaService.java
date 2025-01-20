package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.FarmaciaDAO;
import es.uvigo.dagss.recetas.daos.RecetaDAO;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.services.estrategias.EstrategiaGeneracionRecetas;

@Service
public class RecetaService {
    @Autowired
    private RecetaDAO recetaDAO;

    @Autowired
    private FarmaciaDAO farmaciaDAO;

    @Autowired
    @Qualifier("estrategiaGeneracionRecetasSemanal")
    private EstrategiaGeneracionRecetas estrategiaGeneracionRecetas;

    public RecetaService() {
    }

    public void setEstrategiaGeneracionRecetas(EstrategiaGeneracionRecetas estrategia) {
        this.estrategiaGeneracionRecetas = estrategia;
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

    // HU-M5: Generación de "planes de recetas" para una prescripción
    public List<Receta> generarPlanRecetas(Prescripcion prescripcion) {
        return estrategiaGeneracionRecetas.generarPlanRecetas(prescripcion);
    }

    public List<Receta> getRecetasPorPrescripcion(Long prescripcionId) {
        return recetaDAO.findByPrescripcionId(prescripcionId);
    }
}
