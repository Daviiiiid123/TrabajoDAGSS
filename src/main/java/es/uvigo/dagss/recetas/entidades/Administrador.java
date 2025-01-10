package es.uvigo.dagss.recetas.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.TableGenerator;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {

    @Id
    @TableGenerator(name = "ADMINISTRADOR_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ADMINISTRADOR_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADMINISTRADOR_GEN")
    private Long id;

    @Column(name="NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name="EMAIL", length = 50, nullable = false)
    private String email;

    private String login;
    private String password;

    // Añadido para permitir la eliminación lógica
    // HU-A2: Eliminación lógica estableciendo activo a false
    private Boolean activo = true;

    //(1,N) Administrador gestiona (1,N) CentroSalud
    @ManyToMany
    @JoinTable(name = "ADMINISTRADOR_CENTROSALUD",
            joinColumns = @JoinColumn(name = "ADMINISTRADOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "CENTROSALUD_ID"))
    private List<CentroSalud> centrosSalud;

	
    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR); 
    }

    public Administrador(String login, String password, String nombre, String email) {
        super(TipoUsuario.ADMINISTRADOR, login, password);
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.length() > 0){
            this.nombre = nombre;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(validarEmail(email)){
            this.email = email;
        }
    }

    private boolean validarEmail(String email){
        boolean valido = false;
        if(email.contains("@") && email.contains(".")){
            valido = true;
        }
        return valido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", login=" + login + ", password=" + password + ", activo=" + activo + '}';
    }
    
    //Metodo hashCode
    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        return super.hashCode();
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

        Administrador other = (Administrador) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }
        return super.equals(obj);
    }
    

}
