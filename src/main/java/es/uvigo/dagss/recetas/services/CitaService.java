package es.uvigo.dagss.recetas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CitaDAO;
import es.uvigo.dagss.recetas.daos.MedicoDAO;
import es.uvigo.dagss.recetas.daos.PacienteDAO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;

@Service

public class CitaService {

    @Autowired
    private CitaDAO citaDAO;

    @Autowired
    private PacienteDAO pacienteDAO;

    @Autowired
    private MedicoDAO medicoDAO;

    public CitaService() {
    }

    public Cita crear(Cita cita) {
        return citaDAO.save(cita);
    }

    public void eliminar(Long id) {
        citaDAO.deleteById(id);
    }

    public Cita actualizar(Cita cita) {
        return citaDAO.save(cita);
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaDAO.findById(id);
    }

 
    public List<Cita> listarTodas() {
        return citaDAO.findAll();
    }

    // HU-A7: Añadir método para anular citas
    public void anularCita(Long id) {
        Optional<Cita> cita = citaDAO.findById(id);
        if (cita.isPresent()) {
            Cita citaExistente = cita.get();
            citaExistente.setEstadoCita(EstadoCita.ANULADA);
            citaDAO.save(citaExistente);
        }
    }

    // HU-P3: Método para crear una nueva cita para un paciente
    public Cita crearCita(Long pacienteId, Long medicoId, Date fechaHora) {
        Optional<Paciente> paciente = pacienteDAO.findById(pacienteId);
        Optional<Medico> medico = medicoDAO.findById(medicoId);
        if (paciente.isPresent() && medico.isPresent()) {
            Cita nuevaCita = new Cita(paciente.get(), medico.get(), fechaHora, EstadoCita.PLANIFICADA, 15);
            return citaDAO.save(nuevaCita);
        } else {
            throw new RuntimeException("Paciente o Médico no encontrado");
        }
    }

    // HU-P2: Método para anular una cita de un paciente
    public void anularCitaPaciente(Long citaId) {
        Optional<Cita> cita = citaDAO.findById(citaId);
        if (cita.isPresent()) {
            Cita citaExistente = cita.get();
            citaExistente.setEstadoCita(EstadoCita.ANULADA);
            citaDAO.save(citaExistente);
        } else {
            throw new RuntimeException("Cita no encontrada");
        }
    }
}
