package es.uvigo.dagss.recetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CitaDAO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;

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




    


}
