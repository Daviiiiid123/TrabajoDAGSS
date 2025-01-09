package es.uvigo.dagss.recetas.daos;
import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Medicamento;

public interface MedicamentoDAO extends JpaRepository<Medicamento, Long> {
    Medicamento findByNombreComercial(String nombre);
    void deleteByNombreComercial(String nombre);
}
