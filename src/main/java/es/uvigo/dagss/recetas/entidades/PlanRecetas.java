/*package es.uvigo.dagss.recetas.entidades;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TableGenerator;

@Entity
public class PlanRecetas {

    @TableGenerator(name = "PLANRECETAS_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "PLANRECETAS_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PLANRECETAS_GEN")
    @Id
    private Long id;

    @OneToMany(targetEntity = Receta.class)
    private List<Receta> recetas;

    @ManyToOne(targetEntity = Prescripcion.class)
    private Prescripcion prescripcion;

    public PlanRecetas() {
    }

    public PlanRecetas(List<Receta> recetas, Prescripcion prescripcion) {
        this.recetas = recetas;
        this.prescripcion = prescripcion;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public void addReceta(Receta receta) {
        this.recetas.add(receta);
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    public void removeReceta(Receta receta) {
        this.recetas.remove(receta);
    }



}*/
