package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    @Id
    @TableGenerator(name = "FARMACIA_GEN", table = "GENERATOR_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "FARMACIA_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FARMACIA_GEN")
    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String email;

    private String nombreFarmaceutico;

    private String apellidosFarmaceutico;

    private String DNI;

    private String numeroColegiado;


    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }

    public Farmacia(String login, String password, String nombre, String direccion, String telefono, String email, String nombreFarmaceutico, String apellidosFarmaceutico, String DNI, String numeroColegiado) {
        super(TipoUsuario.FARMACIA, login, password);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreFarmaceutico = nombreFarmaceutico;
        this.apellidosFarmaceutico = apellidosFarmaceutico;
        this.DNI = DNI;
        this.numeroColegiado = numeroColegiado;
    }

    
    
    private boolean validarTelefono(String telefono){
        boolean valido = false;
        if(telefono.length() == 9){
            for(int i = 0; i < telefono.length(); i++){
                if(Character.isDigit(telefono.charAt(i))){
                    valido = true;
                }else{
                    valido = false;
                    break;
                }
            }
        }
        return valido;
    }

    private boolean validarDNI(String DNI){
        boolean valido = false;
        if(DNI.length() == 9){
            for(int i = 0; i < DNI.length()-1; i++){
                if(Character.isDigit(DNI.charAt(i))){
                    valido = true;
                }else{
                    valido = false;
                    break;
                }
            }
        }
        return valido;
    }

    private boolean validarEmail(String email){
        boolean valido = false;
        if(email.contains("@") && email.contains(".")){
            valido = true;
        }
        return valido;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(validarTelefono(telefono)){
            this.telefono = telefono;
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

    public String getNombreFarmaceutico() {
        return nombreFarmaceutico;
    }

    public void setNombreFarmaceutico(String nombreFarmaceutico) {
        this.nombreFarmaceutico = nombreFarmaceutico;
    }

    public String getApellidosFarmaceutico() {
        return apellidosFarmaceutico;
    }

    public void setApellidosFarmaceutico(String apellidosFarmaceutico) {
        this.apellidosFarmaceutico = apellidosFarmaceutico;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        if(validarDNI(DNI)){
        this.DNI = DNI;
        }
    }

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    //toString
    @Override
    public String toString() {
        return "Farmacia{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + ", nombreFarmaceutico=" + nombreFarmaceutico + ", apellidosFarmaceutico=" + apellidosFarmaceutico + ", DNI=" + DNI + ", numeroColegiado=" + numeroColegiado + '}';
    }

    //Equals y HashCode
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Farmacia other = (Farmacia) obj;
        if (this.id != null) {
            return this.id.equals(other.id);
        }
        return super.equals(obj);
    }
    
}
