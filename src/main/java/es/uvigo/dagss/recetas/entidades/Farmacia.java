package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    // Anadir atributos propios

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
}
