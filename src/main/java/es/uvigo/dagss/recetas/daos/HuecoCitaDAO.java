/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.HuecoCita;
import es.uvigo.dagss.recetas.entidades.Medico;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author suso
 */
public interface HuecoCitaDAO extends JpaRepository<HuecoCita, Long>{
    HuecoCita findByMedicoAndDiaAndNumeroHueco(Medico medico, Date dia,int numeroHoueco);
    List<HuecoCita> findByMedicoAndDia(Medico medico, Date dia);
}
