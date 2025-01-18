/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author suso
 */
@Entity
public class HuecoCita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Column(name = "dia")
    Date dia;

    @NotNull
    @Column(name = "numeroHueco")
    Integer numeroHueco;

    @NotNull
    @Column(name = "medico")
    @ManyToOne
    Medico medico;

    @NotNull
    @OneToOne
    Cita cita;

    public HuecoCita() {
    }

    public HuecoCita(long id, Date dia, Integer numeroHueco, Medico medico, Cita cita) {
        this.id = id;
        this.dia = dia;
        this.numeroHueco = numeroHueco;
        this.medico = medico;
        this.cita = cita;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Date getDia() {
        return dia;
    }

    public Integer getNumeroHueco() {
        return numeroHueco;
    }

    public void setNumeroHueco(Integer numeroHueco) {
        this.numeroHueco = numeroHueco;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public static int[] numeroHuecoToHoraMinuto(int numeroHueco) {
        int minutos = numeroHueco * 15;
        int horas = minutos / 60;
        minutos = minutos % 60;

        return new int[]{horas, minutos};
    }

    public Date getFechaHora() {
        Date toret = (Date) this.dia.clone();
        //8:30 a 15:30, huecos de 15 min
        int[] horasMinutos = HuecoCita.numeroHuecoToHoraMinuto(this.numeroHueco);
        int horas = horasMinutos[0];
        int minutos = horasMinutos[1];

        Calendar cal = Calendar.getInstance();
        cal.setTime(toret);
        cal.set(Calendar.HOUR, horas);
        cal.set(Calendar.MINUTE, minutos);
        return cal.getTime();
    }

    public void setFechaHora(Date fechaHora) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaHora);

        Calendar minHour = (Calendar) cal.clone();
        minHour.set(Calendar.HOUR, 8);
        minHour.set(Calendar.MINUTE, 30);

        Calendar maxHour = (Calendar) cal.clone();
        maxHour.set(Calendar.HOUR, 15);
        maxHour.set(Calendar.MINUTE, 30);

        if (cal.before(minHour) || cal.after(maxHour)) {
            throw new IllegalArgumentException("Las citas deben ser entre 8:30 y 15:30");
        }
        this.numeroHueco = (cal.get(Calendar.HOUR) * 60 + cal.get(Calendar.MINUTE)) / 15;

        this.dia = cal.getTime();
    }

}
