package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.FarmaciaDAO;
import es.uvigo.dagss.recetas.entidades.Farmacia;

@Service

public class FarmaciaService {

    @Autowired
    private FarmaciaDAO farmaciaDAO;

    public FarmaciaService() {
    }

    public void crearFarmacia(Farmacia farmacia) {
        farmaciaDAO.save(farmacia);
    }

    public void eliminarFarmacia(Farmacia farmacia) {
        farmaciaDAO.delete(farmacia);
    }

    public void actualizarFarmacia(Farmacia farmacia) {
        farmaciaDAO.save(farmacia);
    }

    public List<Farmacia> buscarTodos() {
        return farmaciaDAO.findAll();
    }

    public Farmacia buscarPorId(Long id) {
        return farmaciaDAO.findById(id).orElse(null);
    }

    public Farmacia buscarPorNombreExacto(String nombre) {
        return farmaciaDAO.findByNombre(nombre);
    }



    public Farmacia buscarPorTelefono(String telefono) {
        return farmaciaDAO.findByTelefono(telefono);
    }

    public Farmacia buscarPorEmail(String email) {
        return farmaciaDAO.findByEmail(email);
    }

    public Farmacia buscarPorNumeroColegiado(String numeroColegiado) {
        return farmaciaDAO.findByNumeroColegiado(numeroColegiado);
    }

    public List<Farmacia> obtenerFarmacias() {
        return farmaciaDAO.findAll();
    }

    public void eliminarFarmaciaPorId(Long id) {
        farmaciaDAO.deleteById(id);
    }

    public void eliminarFarmaciaPorNombre(String nombre) {
        farmaciaDAO.deleteByNombre(nombre);
    }

    // HU-A6: Añadir método para encontrar farmacias activas
    public List<Farmacia> findActiveFarmacias() {
        return farmaciaDAO.findByActivoTrue();
    }

}
