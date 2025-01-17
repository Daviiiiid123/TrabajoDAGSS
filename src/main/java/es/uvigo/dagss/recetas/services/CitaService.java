package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CitaDAO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.EstadoCita;

@Service

public class CitaService {

    @Autowired
    private CitaDAO citaDAO;

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
    
    public List<Cita> buscarPorMedico(Medico medico){
        return citaDAO.findByMedico(medico);
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

}
