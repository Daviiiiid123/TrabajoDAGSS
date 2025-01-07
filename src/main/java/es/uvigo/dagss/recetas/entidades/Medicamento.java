package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//@Entity
public class Medicamento {
    //Identificador unico del medicamento
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDICAMENTO_GEN")
    private Long id;

    //Nombre comercial del medicamento
    @Column(name = "NOMBRE_COMERCIAL", length = 30, nullable = false)
    private String nombreComercial;

    //Principio activo del medicamento
    @Column(name = "PRINCIPIO_ACTIVO", length = 50, nullable = false)
    private String principioActivo;

    //Familia del medicamento
    @Column(name = "FAMILIA", length = 50, nullable = false)
    private String familia;

    //Numero de dosis por envase
    @Column(name = "DOSIS", length = 50, nullable = false)
    private int dosis;

    //Cantidad de existencias del medicamento
    @Column(name = "EXISTENCIAS", length = 50, nullable = false)
    private int existencias;

    //Booleano que indica si el medicamento está activo
    @Column(name = "ACTIVO", length = 50, nullable = false)
    private boolean activo;

    //(1,1) Prescripcion preescribe (1,N) Medicamento
    @ManyToOne
    @JoinColumn(name = "PRESCRIPCION_ID", nullable = false)
    private Prescripcion prescripcion;


    //Constructor vacio porque es necesario para JPA
    public Medicamento() {
    }

    //Constructor con parametros
    public Medicamento(String nombreComercial, String principioActivo, String familia, int dosis, int existencias, boolean activo) {
        this.nombreComercial = nombreComercial;
        this.principioActivo = principioActivo;
        this.familia = familia;
        this.dosis = dosis;
        this.existencias = existencias;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Medicamento{" + "id=" + id + ", nombreComercial=" + nombreComercial + ", principioActivo=" + principioActivo + ", familia=" + familia + ", dosis=" + dosis + ", existencias=" + existencias + ", activo=" + activo + '}';
    }

    //Metodo equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Medicamento other = (Medicamento) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }
        return this.nombreComercial.equals(other.getNombreComercial())
                && this.principioActivo.equals(other.getPrincipioActivo())
                && this.familia.equals(other.getFamilia())
                && this.dosis == other.getDosis()
                && this.existencias == other.getExistencias()
                && this.activo == other.isActivo();
    }


}
