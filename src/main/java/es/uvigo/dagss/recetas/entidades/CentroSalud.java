package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.TableGenerator;

@Entity
public class CentroSalud {

    @Id
    @TableGenerator(name = "USUARIO_GEN", table = "USUARIO_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)           
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USUARIO_GEN")
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;
    private boolean activo;
    private String email;

    @ManyToOne(targetEntity = Administrador.class)
    private Administrador administrador;


    public CentroSalud() {
    }

    public CentroSalud(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 0) {
            this.nombre = nombre;
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion.length() > 0) {
            this.direccion = direccion;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (validarTelefono(telefono)) {
            this.telefono = telefono;
        }
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    private boolean validarTelefono(String telefono) {
        boolean valido = false;
        if (telefono.length() == 9) {
            valido = true;
        }
        return valido;
    }

}
