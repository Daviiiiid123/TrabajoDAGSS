package es.uvigo.dagss.recetas.daos;
import org.springframework.data.jpa.repository.JpaRepository;

import es.uvigo.dagss.recetas.entidades.Medicamento;

public interface MedicamentoDAO extends JpaRepository<Medicamento, Long> {
    Medicamento findByNombre(String nombre);
    Medicamento findByDescripcion(String descripcion);
    Medicamento findByPrecio(String precio);
    Medicamento findByActivoTrue();
    void deleteByNombre(String nombre);
}
