package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {

    @Id
    @TableGenerator(name = "ADMINISTRADOR_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "ADMINISTRADOR_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADMINISTRADOR_GEN")
    private Long id;

    private String nombre;

    private String email;


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
        if (nombre.length() > 0) {
            this.nombre = nombre;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        }
    }

    private boolean validarEmail(String email) {
        boolean valido = false;
        if (email.contains("@") && email.contains(".")) {
            valido = true;
        }
        return valido;
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
