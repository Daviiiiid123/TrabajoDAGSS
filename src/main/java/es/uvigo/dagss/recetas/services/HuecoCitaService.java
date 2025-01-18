/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uvigo.dagss.recetas.services;

import es.uvigo.dagss.recetas.daos.HuecoCitaDAO;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.HuecoCita;
import es.uvigo.dagss.recetas.entidades.Medico;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author suso
 */
@Service

public class HuecoCitaService {

    @Autowired
    HuecoCitaDAO huecoCitaDAO;

    public HuecoCitaService() {
    }

    //huecos por medico y dia que no tienen una cita en estado planificado
    // HU-P3
    public boolean[] HuecosDisponiblesPorMedicoYDia(Medico medico, Date dia) {
        int minutosIniciales = 8 * 60 + 30;
        int minutosFinales = 15 * 60 + 30;
        int minutosHueco = 15;

        boolean[] disponibles = new boolean[(minutosFinales - minutosIniciales) / minutosHueco];
        for (int i = 0; i < disponibles.length; i++) {
            disponibles[i] = true;
        }

        List<HuecoCita> usados = this.huecoCitaDAO.findByMedicoAndDia(medico, dia);
        for (int i = 0; i < usados.size(); i++) {
            if(usados.get(i).getCita().getEstadoCita() == EstadoCita.PLANIFICADA)
                disponibles[usados.get(i).getNumeroHueco()] = false;
        }
        return disponibles;
    }
}
