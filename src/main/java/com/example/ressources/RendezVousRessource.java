package com.example.ressources;

import com.example.dto.RendezVousCreatedDTO;
import com.example.dto.RendezVousDTO;
import com.example.entitys.RendezVous;
import com.example.mapper.RendezVousMapper;
import com.example.service.RendezVousService;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/rendezvous")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RendezVousRessource {
    @Inject
    private RendezVousService rendezVousService;
    
    @POST
    public Response createRendezVous(RendezVousCreatedDTO rendezVousCreatedDTO){
        RendezVous rendezVous = RendezVousMapper.toEntity(rendezVousCreatedDTO);
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
}