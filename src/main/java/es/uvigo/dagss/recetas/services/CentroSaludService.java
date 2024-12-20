package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CentroSaludDAO;
import es.uvigo.dagss.recetas.entidades.CentroSalud;

@Service

public class CentroSaludService {

    @Autowired
    private CentroSaludDAO centroSaludDAO;

    public CentroSaludService() {
    }

    public List<CentroSalud> buscarPorNombre(String nombre) {
        return centroSaludDAO.findByNombreContains(nombre);
    }

    public void crearCentroSalud(CentroSalud centroSalud) {
        centroSaludDAO.save(centroSalud);
    }


    
}
