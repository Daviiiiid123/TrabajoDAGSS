package es.uvigo.dagss.recetas.services;


import es.uvigo.dagss.recetas.daos.PacienteDAO;
import es.uvigo.dagss.recetas.entidades.Paciente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class PacienteService {

    @Autowired
    private PacienteDAO pacienteDAO;

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
}