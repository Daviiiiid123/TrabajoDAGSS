package es.uvigo.dagss.recetas.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TableGenerator;

@Entity
public class CentroSalud {

    @Id
    @TableGenerator(name = "USUARIO_GEN", table = "USUARIO_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VAL", allocationSize = 1)           
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USUARIO_GEN")
    private Long id;

    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;

    @Column(name = "DIRECCION", length = 200, nullable = false)
    private String direccion;

    @Column(name = "TELEFONO", length = 15, nullable = false)
    private String telefono;

    @Column(name = "ACTIVO", nullable = false)
    private boolean activo;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    //(1,N) CentroSalud es gestionado por (1,N) Administrador
    @ManyToMany(targetEntity = Administrador.class)
    @JoinTable(name = "ADMINISTRADOR_CENTROSALUD",
            joinColumns = @JoinColumn(name = "CENTROSALUD_ID"),
            inverseJoinColumns = @JoinColumn(name = "ADMINISTRADOR_ID"))   
    private List<Administrador> administradores;

    //(1,N) CentroSalud tiene (1,1) Medico
    @ManyToOne(targetEntity = Medico.class)
    @JoinColumn(name = "MEDICO_ID")
    private Medico medico;

    //(1,1) CentroSalud tiene (1,N) Paciente
    @OneToMany(targetEntity = Paciente.class)
    @JoinColumn(name = "CENTROSALUD_ID")
    private List<Paciente> pacientes;
    



    public CentroSalud() {
    }

    public CentroSalud(String nombre, String direccion, String telefono, boolean activo, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CentroSalud{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", activo=" + activo + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CentroSalud other = (CentroSalud) obj;
        if (this.id != null) {
            return this.id.equals(other.getId());
        }
        return super.equals(obj);
    }


}
