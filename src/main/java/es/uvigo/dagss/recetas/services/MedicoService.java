package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.MedicoDAO;
import es.uvigo.dagss.recetas.entidades.Medico;

@Service

public class MedicoService {

    @Autowired
    private MedicoDAO medicoDAO;

    public MedicoService() {
    }

    public void crearMedico(Medico medico) {
        medicoDAO.save(medico);
    }

    public void eliminarMedico(Medico medico) {
        medicoDAO.delete(medico);
    }

    public void actualizarMedico(Medico medico) {
        medicoDAO.save(medico);
    }

    public List<Medico> buscarTodos() {
        return medicoDAO.findAll();
    }

    public Medico buscarPorId(Long id) {
        return medicoDAO.findById(id).orElse(null);
    }

    public Medico buscarPorNombreExacto(String nombre) {
        return medicoDAO.findByNombre(nombre);
    }

    public Medico buscarPorTelefono(String telefono) {
        return medicoDAO.findByTelefono(telefono);
    }

    public Medico buscarPorEmail(String email) {
        return medicoDAO.findByEmail(email);
    }

    public Medico buscarPorNumeroColegiado(String numeroColegiado) {
        return medicoDAO.findByNumeroColegiado(numeroColegiado);
    }

    public List<Medico> obtenerMedicos() {
        return medicoDAO.findAll();
    }

    public List<Medico> buscarPorNombre(String nombre) {
        return medicoDAO.findByNombreContains(nombre);
    }

    public List<Medico> buscarActivos() {
        return medicoDAO.findByActivoTrue();
    }

    public void eliminarMedico(Long id) {
        medicoDAO.deleteById(id);
    }

    public void eliminarMedico(String nombre) {
        medicoDAO.deleteByNombre(nombre);
    }

    
}
