package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class Cita implements Serializable {
//Identificador unico de la cita

    @Id

//Generador de tabla para la generacion de claves primarias
    @TableGenerator(name = "CITA_GEN", table = "CITA_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)

    //Generacion de clave primaria
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CITA_GEN")
    private Long id;

    //Paciente citado
    @ManyToOne //Una cita tiene un unico paciente
    private Paciente pacienteCitado;

    //Fecha y hora de la cita
    @OneToOne
    private HuecoCita huecoCita;

    //estado (PLANIFICADA, ANULADA, COMPLETADA, AUSENTE)
    @Enumerated(EnumType.STRING)
    private EstadoCita estadoCita;

    //Duracion en minutos
    @Value("15")
    private int duracion;

//Constructor vacio porque es necesario para JPA
    public Cita() {
    }

    /*Constructor con parametros
* @param pacienteCitado Paciente que tiene la cita
* @param fechaHora Fecha y hora de la cita
* @param estadoCita Estado de la cita
* @param duracion Duracion de la cita en minutos
     */

    public Cita(Long id, Paciente pacienteCitado, HuecoCita huecoCita, EstadoCita estadoCita, int duracion) {
        this.id = id;
        this.pacienteCitado = pacienteCitado;
        this.huecoCita = huecoCita;
        this.estadoCita = estadoCita;
        this.duracion = duracion;
    }
 
    
//Getters y Setters
    public Long getId() {
        return id;
    }
    
    public Date getFechaHora(){
        return this.huecoCita.getFechaHora();
    }
    
    public Paciente getPacienteCitado() {
        return pacienteCitado;
    }

    public void setPacienteCitado(Paciente pacienteCitado) {
        this.pacienteCitado = pacienteCitado;
    }

    public Medico getMedicoAtiende() {
        return this.huecoCita.getMedico();
    }

    public void setMedicoAtiende(Medico medicoAtiende) {
        this.huecoCita.setMedico(medicoAtiende);
    }

    public EstadoCita getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(EstadoCita estadoCita) {
        this.estadoCita = estadoCita;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Cita{" + "id=" + id + ", pacienteCitado=" + pacienteCitado + ", medicoAtiende=" + this.huecoCita.getMedico() + ", fechaHora=" + this.getFechaHora() + ", estadoCita=" + estadoCita + ", duracion=" + duracion + '}';
    }

    //Metodos Historias de Usuario
    public void anularCita() {
        this.estadoCita = EstadoCita.ANULADA;
    }

}
