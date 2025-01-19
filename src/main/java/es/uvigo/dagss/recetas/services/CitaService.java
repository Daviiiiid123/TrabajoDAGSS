package es.uvigo.dagss.recetas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.CitaDAO;
import es.uvigo.dagss.recetas.daos.MedicoDAO;
import es.uvigo.dagss.recetas.daos.PacienteDAO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import java.util.ArrayList;
import java.util.Calendar;

@Service

public class CitaService {

    @Autowired
    private CitaDAO citaDAO;

    @Autowired
    private PacienteDAO pacienteDAO;

    @Autowired
    private MedicoDAO medicoDAO;

    public CitaService() {
    }

    public Cita crear(Cita cita) {
        return citaDAO.save(cita);
    }

    public void eliminar(Long id) {
        citaDAO.deleteById(id);
    }

    public Cita actualizar(Cita cita) {
        return citaDAO.save(cita);
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaDAO.findById(id);
    }

 
    public List<Cita> listarTodas() {
        return citaDAO.findAll();
    }
    
    public List<Cita> buscarPorMedico(Medico medico){
        return citaDAO.findByMedicoAtiende(medico);
    }
    
    
    public List<String> HuecosVaciosMedicoEnDia(Medico medico, Date dia){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dia);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        Date inicio = cal.getTime();
        
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date fin = cal.getTime();
        
        List<Cita> citas = citaDAO.findByMedicoAtiendeAndFechaHoraBetweenAndEstadoCita(medico, inicio, fin,EstadoCita.PLANIFICADA);
        List<String> huecos = getHuecosPosibles();
        
        int huecoCita = 0;
        for(var cita : citas){
            cal.setTime(cita.getFechaHora());
            huecoCita = horaAHueco(cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE));
            huecos.set(huecoCita, "HUECO OCUPADO");
        }
        
        return huecos;
    }
    
    List<String> getHuecosPosibles(){
        ArrayList<String> huecos = new ArrayList<>();
        huecos.ensureCapacity(Cita.NUMERO_HUECOS);
        
        for (int i = 0 ; i < Cita.NUMERO_HUECOS; i++) {
            huecos.add(horaHueco(i) + ":"+ minutoHueco(i) + " - " + horaHueco(i+1) + ":" + minutoHueco(i+1));
        }
        return huecos;
    }
    
    public static int horaAHueco(int hora, int minutos){
        return (hora * 60 + minutos - Cita.MINUTOS_APERTURA) / Cita.MINUTOS_HUECO;
    }
    public static int horaHueco(int numHueco){
        return (Cita.MINUTOS_APERTURA + numHueco * Cita.MINUTOS_HUECO) / 60;
    }
    
    public static int minutoHueco(int numHueco){
        return (Cita.MINUTOS_APERTURA + numHueco * 15) % 60; 
    }

    // HU-A7: Añadir método para anular citas
    public void anularCita(Long id) {
        Optional<Cita> cita = citaDAO.findById(id);
        if (cita.isPresent()) {
            Cita citaExistente = cita.get();
            citaExistente.setEstadoCita(EstadoCita.ANULADA);
            citaDAO.save(citaExistente);
        }
    }

    // HU-P3: Método para crear una nueva cita para un paciente
    public Cita crearCita(Long pacienteId, Long medicoId, Date fechaHora) {
        Optional<Paciente> paciente = pacienteDAO.findById(pacienteId);
        Optional<Medico> medico = medicoDAO.findById(medicoId);
        if (paciente.isPresent() && medico.isPresent()) {
            Cita nuevaCita = new Cita(paciente.get(), medico.get(), fechaHora, EstadoCita.PLANIFICADA, 15);
            return citaDAO.save(nuevaCita);
        } else {
            throw new RuntimeException("Paciente o Médico no encontrado");
        }
    }

    // HU-P2: Método para anular una cita de un paciente
    public void anularCitaPaciente(Long citaId) {
        Optional<Cita> cita = citaDAO.findById(citaId);
        if (cita.isPresent()) {
            Cita citaExistente = cita.get();
            citaExistente.setEstadoCita(EstadoCita.ANULADA);
            citaDAO.save(citaExistente);
        } else {
            throw new RuntimeException("Cita no encontrada");
        }
    }

}
