package es.uvigo.dagss.recetas.services.estrategias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uvigo.dagss.recetas.daos.RecetaDAO;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;

/**
 * Estrategia de generación de recetas que crea recetas de manera semanal.
 * Cada receta tiene un margen de validez de una semana antes y después de la fecha exacta.
 */
@Component
public class EstrategiaGeneracionRecetasSemanal implements EstrategiaGeneracionRecetas {
    @Autowired
    private RecetaDAO recetaDAO;

    @Override
    public List<Receta> generarPlanRecetas(Prescripcion prescripcion) {
        List<Receta> planRecetas = new ArrayList<>();
        int dosisDiaria = prescripcion.getDosis();
        int dosisPorEnvase = prescripcion.getMedicamento().getDosis();
        int totalDias = (int) ((prescripcion.getFechaFin().getTime() - prescripcion.getFechaInicio().getTime()) / 86400000);
        int totalEnvases = (int) Math.ceil((double) (dosisDiaria * totalDias) / dosisPorEnvase);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prescripcion.getFechaInicio());

        for (int i = 0; i < totalEnvases; i++) {
            Date fechaInicio = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, dosisPorEnvase / dosisDiaria);
            Date fechaFin = calendar.getTime();

            // Ajustar las fechas de validez con un margen de una semana antes y después
            Calendar fechaValidezInicio = Calendar.getInstance();
            fechaValidezInicio.setTime(fechaInicio);
            fechaValidezInicio.add(Calendar.DAY_OF_MONTH, -7);

            Calendar fechaValidezFin = Calendar.getInstance();
            fechaValidezFin.setTime(fechaFin);
            fechaValidezFin.add(Calendar.DAY_OF_MONTH, 7);

            // Crear receta con varias cajas para minimizar visitas a la farmacia
            int cajasPorReceta = 1; // Ajustar este valor según sea necesario
            Receta receta = new Receta(prescripcion, fechaValidezInicio.getTime(), fechaValidezFin.getTime(), cajasPorReceta, Receta.Estado.PLANIFICADA);
            planRecetas.add(receta);
            recetaDAO.save(receta);
        }

        return planRecetas;
    }
}
