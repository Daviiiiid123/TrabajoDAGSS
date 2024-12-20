package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {

    // Anadir atributos propios

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

}
