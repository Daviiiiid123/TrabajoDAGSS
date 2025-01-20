package es.uvigo.dagss.recetas.services.estrategias;

import java.util.List;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;

public interface EstrategiaGeneracionRecetas {
    List<Receta> generarPlanRecetas(Prescripcion prescripcion);
}
