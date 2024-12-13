package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario {

	// Anadir atributos propios

    private String telefono;

    private String DNI;

    private String direccion;

    private String NSS;

    private String email;

    private String nombre;
   

    public Paciente() {
        super(TipoUsuario.PACIENTE);        
    }

    public Paciente(String login, String password, String telefono, String DNI, String direccion, String NSS, String email, String nombre) {
        super(TipoUsuario.PACIENTE, login, password);
        this.telefono = telefono;
        this.DNI = DNI;
        this.direccion = direccion;
        this.NSS = NSS;
        this.email = email;
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(validarTelefono(telefono)){
            this.telefono = telefono;
        }        
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if(direccion.length() > 0){
            this.direccion = direccion;
        }
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        if(validarDNI(DNI)){
            this.DNI = DNI;
        }
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
            this.NSS = NSS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(validarEmail(email)){
            this.email = email;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre.length() > 0){
            this.nombre = nombre;
        }
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




}
