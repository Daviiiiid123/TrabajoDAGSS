package es.uvigo.dagss.recetas.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CitaDAO;
import es.uvigo.dagss.recetas.daos.PacienteDAO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Paciente;


@Service

public class PacienteService {

    @Autowired
    private PacienteDAO pacienteDAO;

    @Autowired
    private CitaDAO citaDAO;

    public PacienteService() {
    }



    public Paciente createPaciente(Paciente paciente) {
        return pacienteDAO.save(paciente);
    }

    public Paciente updatePaciente(Paciente paciente) {
        return pacienteDAO.save(paciente);
    }

    public void deletePaciente(Long id) {
        pacienteDAO.deleteById(id);
    }

    public Optional<Paciente> findPacienteById(Long id) {
        return pacienteDAO.findById(id);
    }

    public List<Paciente> findAllPacientes() {
        return pacienteDAO.findAll();
    }

    // HU-A5: Añadir método para encontrar pacientes activos
    public List<Paciente> findActivePacientes() {
        return pacienteDAO.findByActivoTrue();
    }

    // HU-P1: Método para obtener el "Home" de un paciente
    public String getPacienteHome(Long pacienteId) {
        Optional<Paciente> paciente = pacienteDAO.findById(pacienteId);
        if (paciente.isPresent()) {
            return "Home de Paciente: " + paciente.get().getNombre();
        } else {
            throw new RuntimeException("Paciente no encontrado");
        }
    }

    // HU-P2: Método para obtener las citas planificadas de un paciente
    public List<Cita> getCitasPlanificadas(Long pacienteId) {
        Optional<Paciente> paciente = pacienteDAO.findById(pacienteId);
        if (paciente.isPresent()) {
            return citaDAO.findByPacienteAndEstado(paciente.get(), "PLANIFICADA");
        } else {
            throw new RuntimeException("Paciente no encontrado");
        }
    }

    // HU-P5: Método para modificar las credenciales de acceso de un paciente
    public Paciente modificarCredenciales(Long pacienteId, String nuevoPassword) {
        Optional<Paciente> paciente = pacienteDAO.findById(pacienteId);
        if (paciente.isPresent()) {
            Paciente pacienteExistente = paciente.get();
            pacienteExistente.setPassword(nuevoPassword);
            return pacienteDAO.save(pacienteExistente);
        } else {
            throw new RuntimeException("Paciente no encontrado");
        }
    }

    // HU-P5: Método para actualizar los datos básicos de un paciente
    public Paciente actualizarDatosBasicos(Long pacienteId, Paciente nuevosDatos) {
        Optional<Paciente> paciente = pacienteDAO.findById(pacienteId);
        if (paciente.isPresent()) {
            Paciente pacienteExistente = paciente.get();
            pacienteExistente.setTelefono(nuevosDatos.getTelefono());
            pacienteExistente.setDireccion(nuevosDatos.getDireccion());
            pacienteExistente.setEmail(nuevosDatos.getEmail());
            pacienteExistente.setNombre(nuevosDatos.getNombre());
            return pacienteDAO.save(pacienteExistente);
        } else {
            throw new RuntimeException("Paciente no encontrado");
        }
    }
}