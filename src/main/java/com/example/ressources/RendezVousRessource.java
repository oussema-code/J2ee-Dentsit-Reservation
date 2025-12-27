package com.example.ressources;

import com.example.dto.RendezVousCreatedDTO;
import com.example.dto.RendezVousDTO;
import com.example.entitys.RendezVous;
import com.example.entitys.Patient;
import com.example.entitys.Dentiste;
import com.example.mapper.RendezVousMapper;
import com.example.service.RendezVousService;
import com.example.service.PatientService;
import com.example.service.DentisteService;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/rendezvous")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RendezVousRessource {
    @Inject
    private RendezVousService rendezVousService;
    
    @Inject
    private PatientService patientService;
    
    @Inject
    private DentisteService dentisteService;
    
    @POST
    public Response createRendezVous(RendezVousCreatedDTO rendezVousCreatedDTO){
        Patient patient = patientService.findById(rendezVousCreatedDTO.getIdP());
        Dentiste dentiste = dentisteService.findById(rendezVousCreatedDTO.getIdD());
        
        if (patient == null || dentiste == null) {
            String errorMsg = patient == null ? "Patient not found" : "Dentiste not found";
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + errorMsg + "\"}")
                    .build();
        }
        
        RendezVous rendezVous = RendezVousMapper.toEntity(rendezVousCreatedDTO, patient, dentiste);
        RendezVous createdRendezVous = rendezVousService.create(rendezVous);
        return Response.status(Response.Status.CREATED).entity(RendezVousMapper.toDTO(createdRendezVous)).build();
    }
    
    @GET
    @Path("/patient/{id}")
    public List<RendezVousDTO> getRendezVousByPatientId(@PathParam("id") int patientId){
        return rendezVousService.findByPatientId(patientId).stream().map(RendezVousMapper::toDTO).toList();
    }
    
    @GET
    @Path("/dentiste/{id}")
    public List<RendezVousDTO> getRendezVousByDentisteId(@PathParam("id") int dentisteId){
        return rendezVousService.findByDentisteId(dentisteId).stream().map(RendezVousMapper::toDTO).toList();
    }
    
    /**
     * Get available time slots for a dentist on a specific date
     * @param dentisteId The dentist ID
     * @param date The date in format yyyy-MM-dd
     * @return List of available time slots (HH:mm format)
     */
    @GET
    @Path("/dentiste/{id}/available-slots")
    public Response getAvailableSlots(
            @PathParam("id") int dentisteId,
            @QueryParam("date") String date) {
        
        if (date == null || date.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Date parameter is required\"}")
                    .build();
        }
        
        try {
            // Get all appointments for this dentist on this date
            List<RendezVous> appointments = rendezVousService.findByDentisteId(dentisteId);
            
            // Parse the date string for comparison
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date targetDate = dateFormat.parse(date);
            
            // Filter by date and exclude cancelled appointments
            List<String> occupiedSlots = appointments.stream()
                    .filter(rdv -> {
                        String rdvDate = dateFormat.format(rdv.getDateRv());
                        return rdvDate.equals(date) && !"ANNULE".equals(rdv.getStatutRv());
                    })
                    .map(rdv -> rdv.getHeureRv().toString().substring(0, 5)) // Convert Time to HH:mm String
                    .collect(Collectors.toList());
            
            // Generate all possible slots (8:00 - 18:00, every 30 minutes)
            List<String> allSlots = generateTimeSlots();
            
            // Remove occupied slots
            List<String> availableSlots = allSlots.stream()
                    .filter(slot -> !occupiedSlots.contains(slot))
                    .collect(Collectors.toList());
            
            return Response.ok(availableSlots).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    /**
     * Check if a specific time slot is available
     * @param dentisteId The dentist ID
     * @param date The date
     * @param time The time
     * @return true if available, false otherwise
     */
    @GET
    @Path("/dentiste/{id}/check-availability")
    public Response checkAvailability(
            @PathParam("id") int dentisteId,
            @QueryParam("date") String date,
            @QueryParam("time") String time) {
        
        if (date == null || time == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Date and time parameters are required\"}")
                    .build();
        }
        
        try {
            List<RendezVous> appointments = rendezVousService.findByDentisteId(dentisteId);
            
            boolean isAvailable = appointments.stream()
                    .noneMatch(rdv -> 
                        rdv.getDateRv().equals(date) && 
                        rdv.getHeureRv().equals(time) &&
                        !"ANNULE".equals(rdv.getStatutRv())
                    );
            
            return Response.ok("{\"available\": " + isAvailable + "}").build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
    
    /**
     * Generate time slots from 8:00 to 18:00 with 30-minute intervals
     */
    private List<String> generateTimeSlots() {
        List<String> slots = new ArrayList<>();
        for (int hour = 8; hour < 18; hour++) {
            slots.add(String.format("%02d:00", hour));
            slots.add(String.format("%02d:30", hour));
        }
        return slots;
    }
}