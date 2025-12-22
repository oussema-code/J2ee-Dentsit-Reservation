package com.example.ressources;

import com.example.dto.PatientCreatedDTO;
import com.example.entitys.Patient;
import com.example.mapper.PatientMapper;
import com.example.service.PatientService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientRessource {
    @Inject
    private PatientService patientService;
    
    @POST
    public Response createPatient(PatientCreatedDTO dto) {
        Patient patient=PatientMapper.toEntity(dto);
        Patient createdPatient = patientService.create(patient);
        
    }
    @GET
    public Response getAllPatients(){
        if(patientService.getAll().isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(patientService.getAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") int id){
        Patient patient=patientService.findById(id);
        if(patient==null){
            return Response.status(Response.Status.NOT_FOUND).build();

        }
        return Response.ok(patient).build();

    }
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id){
        Patient patient =patientService.findById(id);
        if(patient==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        patientService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @PUT
    @Path("/{id}")
    public Response upadatePatient(@PathParam("id") int id, Patient patient){
        Patient existingPatient = patientService.findById(id);
        if(existingPatient == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        patient.setId(id);// ensure the ID is set and hibernate knows it's an update and dont consider it a new entity
        Patient updatedPatient = patientService.update(patient);
        return Response.ok(updatedPatient).build();
    }
}