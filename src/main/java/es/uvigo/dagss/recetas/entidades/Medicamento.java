package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
public class Medicamento {
    //Identificador unico del medicamento
    @Id
    @TableGenerator(name = "MEDICAMENTO_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "MEDICAMENTO_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDICAMENTO_GEN")
    private Long id;

    //Nombre comercial del medicamento
    private String nombreComercial;

    //Principio activo del medicamento
    private String principioActivo;

    //Familia del medicamento
    private String familia;

    //Numero de dosis por envase
    private int dosis;

    //Cantidad de existencias del medicamento
    private int existencias;

    //Fabricante del medicamento
    private String fabricante;

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    //Booleano que indica si el medicamento está activo
    private boolean activo;



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

}
