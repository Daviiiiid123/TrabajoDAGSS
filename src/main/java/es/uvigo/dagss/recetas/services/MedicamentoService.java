package es.uvigo.dagss.recetas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.MedicamentoDAO;
import es.uvigo.dagss.recetas.entidades.Medicamento;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoDAO medicamentoDAO;

    public MedicamentoService() {
    }

    public Medicamento crearMedicamento(Medicamento medicamento) {
        return medicamentoDAO.save(medicamento);
    }

    public Medicamento actualizarMedicamento(Medicamento medicamento) {
        return medicamentoDAO.save(medicamento);
    }

    public void eliminarMedicamento(Long id) {
        Medicamento medicamento = medicamentoDAO.findById(id).orElse(null);
        if (medicamento != null) {
            medicamento.setActivo(false);
            medicamentoDAO.save(medicamento);
        }
    }

    public List<Medicamento> buscarTodos() {
        return medicamentoDAO.findAll();
    }

    public Medicamento buscarPorId(Long id) {
        return medicamentoDAO.findById(id).orElse(null);
    }

    public List<Medicamento> buscarPorNombreComercial(String nombreComercial) {
        return medicamentoDAO.findByNombreComercialContaining(nombreComercial);
    }

    public List<Medicamento> buscarPorPrincipioActivo(String principioActivo) {
        return medicamentoDAO.findByPrincipioActivoContaining(principioActivo);
    }

    public List<Medicamento> buscarPorFabricante(String fabricante) {
        return medicamentoDAO.findByFabricanteContaining(fabricante);
    }

    public List<Medicamento> buscarPorFamilia(String familia) {
        return medicamentoDAO.findByFamiliaContaining(familia);
    }

    public List<Medicamento> buscarActivos() {
        return medicamentoDAO.findByActivoTrue();
    }
}
