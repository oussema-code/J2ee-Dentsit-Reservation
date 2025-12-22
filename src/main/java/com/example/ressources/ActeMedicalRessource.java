package com.example.ressources;

import com.example.dto.ActeMedicalCreatedDTO;
import com.example.dto.ActeMedicalDTO;
import com.example.entitys.ActeMedical;
import com.example.mapper.ActeMedicalMapper;
import com.example.service.ActeMedicalService;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/actemedicals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActeMedicalRessource {
    @Inject
    private ActeMedicalService acteMedicalService;
    
    @POST
    public Response createActeMedical(ActeMedicalCreatedDTO acteMedicalCreatedDTO){
        ActeMedical acteMedical = ActeMedicalMapper.toEntity(acteMedicalCreatedDTO);
        ActeMedical createdActeMedical = acteMedicalService.create(acteMedical);
        return Response.status(Response.Status.CREATED).entity(ActeMedicalMapper.toDTO(createdActeMedical)).build();
    }
    
    @GET
    @Path("/RendezVous/{id}")
    public List<ActeMedicalDTO> getActeMedicalsByRendezVousId(@PathParam("id") int rendezVousId){
        return acteMedicalService.findByRendezVousId(rendezVousId).stream().map(ActeMedicalMapper::toDTO).toList();
    }
}
