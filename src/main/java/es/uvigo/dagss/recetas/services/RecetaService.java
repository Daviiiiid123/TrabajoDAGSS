package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.RecetaDAO;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;

@Service

public class RecetaService {
    @Autowired
    private RecetaDAO recetaDAO;

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

}