package com.example.ressources;

import com.example.dto.AideSoignantDTO;
import com.example.dto.LoginRequest;
import com.example.dto.PatientDTO;
import com.example.entitys.AideSoignant;
import com.example.entitys.Patient;
import com.example.mapper.AideSoignantMapper;
import com.example.mapper.PatientMapper;
import com.example.service.AideSoignantService;
import com.example.service.PatientService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

@Path("/auth")
@Stateless
public class AuthRessource {

    @Inject
    private PatientService patientService;

    @Inject
    private AideSoignantService aideSoignantService;

    @POST
    @Path("/patient/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginPatient(LoginRequest request) {
        try {
            String hashedPassword = hashPassword(request.getPassword());
            List<Patient> patients = patientService.findAll();
            
            Patient patient = patients.stream()
                    .filter(p -> p.getEmailP().equals(request.getEmail()) && 
                                p.getMdpP().equals(hashedPassword))
                    .findFirst()
                    .orElse(null);
            
            if (patient != null) {
                PatientDTO dto = PatientMapper.toDTO(patient);
                // Remove password from response for security
                dto.setMdpP(null);
                return Response.ok(dto).build();
            }
            
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\": \"Email ou mot de passe incorrect\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Erreur lors de la connexion\"}")
                    .build();
        }
    }

    @POST
    @Path("/aidesoignant/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginAideSoignant(LoginRequest request) {
        try {
            String hashedPassword = hashPassword(request.getPassword());
            List<AideSoignant> aideSoignants = aideSoignantService.findAll();
            
            AideSoignant aideSoignant = aideSoignants.stream()
                    .filter(as -> as.getEmailAS().equals(request.getEmail()) && 
                                 as.getMdpAS().equals(hashedPassword))
                    .findFirst()
                    .orElse(null);
            
            if (aideSoignant != null) {
                AideSoignantDTO dto = AideSoignantMapper.toDTO(aideSoignant);
                // Remove password from response for security
                dto.setMdpAS(null);
                return Response.ok(dto).build();
            }
            
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\": \"Email ou mot de passe incorrect\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Erreur lors de la connexion\"}")
                    .build();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
