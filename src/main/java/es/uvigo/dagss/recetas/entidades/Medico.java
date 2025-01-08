package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    // Anadir atributos propios

    private String numeroColegiado;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String email;

    private String DNI;

    private CentroSalud centroSalud;


    
    public Medico() {
        super(TipoUsuario.MEDICO);
    }

    public Medico(String login, String password, String numeroColegiado, String nombre, String apellidos, String telefono, String email, String DNI) {
        super(TipoUsuario.MEDICO, login, password);
        this.numeroColegiado = numeroColegiado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(validarTelefono(telefono)){
            this.telefono = telefono;
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

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        if(numeroColegiado.length() > 0){
            this.numeroColegiado = numeroColegiado;
        }
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if(apellidos.length() > 0){
            this.apellidos = apellidos;
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

    public CentroSalud getCentroSalud() {
        return centroSalud;
    }

    public void setCentroSalud(CentroSalud centroSalud) {
        this.centroSalud = centroSalud;
    }


}
