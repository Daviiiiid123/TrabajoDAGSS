package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
    @JoinColumn(name = "PACIENTE_ID", nullable = false)
    private Paciente pacienteCitado;

    //Medico que atiende
    @ManyToOne //Una cita tiene un unico medico
    @JoinColumn(name = "MEDICO_ID", nullable = true) //Una cita puede no existir
    private Medico medicoAtiende;

    //Fecha y hora de la cita
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_HORA", length = 20)
    private Date fechaHora;

    //estado (PLANIFICADA, ANULADA, COMPLETADA, AUSENTE)
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    private EstadoCita estadoCita;

    //Duracion en minutos
    @Column(name = "DURACION")
    private int duracion;

//Constructor vacio porque es necesario para JPA
    public Cita() {
    }

    /*Constructor con parametros
* @param pacienteCitado Paciente que tiene la cita
* @param medicoAtiende Medico que atiende la cita
* @param fechaHora Fecha y hora de la cita
* @param estadoCita Estado de la cita
* @param duracion Duracion de la cita en minutos
     */
    public Cita(Paciente pacienteCitado, Medico medicoAtiende, Date fechaHora, EstadoCita estadoCita, int duracion) {
        this.pacienteCitado = pacienteCitado;
        this.medicoAtiende = medicoAtiende;
        this.fechaHora = fechaHora;
        this.estadoCita = estadoCita;
        this.duracion = duracion;
    }

//Getters y Setters
    public Long getId() {
        return id;
    }

    public Paciente getPacienteCitado() {
        return pacienteCitado;
    }

    public void setPacienteCitado(Paciente pacienteCitado) {
        this.pacienteCitado = pacienteCitado;
    }

    public Medico getMedicoAtiende() {
        return medicoAtiende;
    }

    public void setMedicoAtiende(Medico medicoAtiende) {
        this.medicoAtiende = medicoAtiende;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
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
        return "Cita{" + "id=" + id + ", pacienteCitado=" + pacienteCitado + ", medicoAtiende=" + medicoAtiende + ", fechaHora=" + fechaHora + ", estadoCita=" + estadoCita + ", duracion=" + duracion + '}';
    }

    //Metodos Historias de Usuario
    public void anularCita() {
        this.estadoCita = EstadoCita.ANULADA;
    }

}
