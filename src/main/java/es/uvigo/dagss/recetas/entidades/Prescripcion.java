package es.uvigo.dagss.recetas.entidades;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Prescripcion {

    @TableGenerator(name = "PRESCRIPCION_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "PRESCRIPCION_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PRESCRIPCION_GEN")
    @Id
    private Long id;


    @ManyToOne(targetEntity = Medicamento.class)
    private Medicamento medicamento;
    @ManyToOne(targetEntity = Paciente.class)
    private Paciente paciente;
    @ManyToOne(targetEntity = Medico.class)
    private Medico medico;


    private String indicaciones;
    private int dosis;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    private boolean activa;

    public Prescripcion() {
    }

    public Prescripcion(Medicamento medicamento, Paciente paciente, Medico medico, String indicaciones, int dosis, Date fechaInicio, Date fechaFin, boolean activa) {
        this.medicamento = medicamento;
        this.paciente = paciente;
        this.medico = medico;
        this.indicaciones = indicaciones;
        this.dosis = dosis;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = activa;
    }

    //Constructor recomendado
    public Prescripcion(Medicamento medicamento, Paciente paciente, Medico medico, String indicaciones, int dosis, Date fechaFin) {
        this.medicamento = medicamento;
        this.paciente = paciente;
        this.medico = medico;
        this.indicaciones = indicaciones;
        this.dosis = dosis;
        this.fechaInicio = new Date();
        this.fechaFin = fechaFin;
        this.activa = true;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean getActiva() {
        return activa;
    }

    



}
