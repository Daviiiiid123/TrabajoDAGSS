package es.uvigo.dagss.recetas.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TableGenerator;

@Entity
public class Receta {

    @TableGenerator(name = "RECETA_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "RECETA_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "RECETA_GEN")
    @Id
    private Long id;


    @ManyToOne(targetEntity = Prescripcion.class)
    private Prescripcion prescripcion;

    
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidad;

    public Long getId() {
        return id;
    }
    public enum Estado {
        PLANIFICADA, SERVIDA, ANULADA
    }
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Farmacia farmaciaServidora;

    public Receta() {
    }

    public Receta(Prescripcion prescripcion, Date fechaInicio, Date fechaFin, int cantidad, Estado estado) {
        this.prescripcion = prescripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        this.estado = estado;
        this.farmaciaServidora = null;
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFarmaciaServidora(Farmacia farmaciaServidora) {
        this.farmaciaServidora = farmaciaServidora;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public Farmacia getFarmaciaServidora() {
        return farmaciaServidora;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }






}
