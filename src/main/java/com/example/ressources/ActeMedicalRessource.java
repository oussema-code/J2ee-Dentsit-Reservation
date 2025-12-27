package com.example.ressources;

import com.example.dto.ActeMedicalCreatedDTO;
import com.example.dto.ActeMedicalDTO;
import com.example.entitys.ActeMedical;
import com.example.entitys.RendezVous;
import com.example.entitys.ServiceMedical;
import com.example.mapper.ActeMedicalMapper;
import com.example.service.ActeMedicalService;
import com.example.service.RendezVousService;
import com.example.service.ServiceMedicalService;
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
    
    @Inject
    private RendezVousService rendezVousService;
    
    @Inject
    private ServiceMedicalService serviceMedicalService;
    
    @POST
    public Response createActeMedical(ActeMedicalCreatedDTO acteMedicalCreatedDTO){
        RendezVous rendezVous = rendezVousService.findById(acteMedicalCreatedDTO.getIdRv());
        ServiceMedical serviceMedical = serviceMedicalService.findById(acteMedicalCreatedDTO.getNumSM());
        
        if (rendezVous == null || serviceMedical == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        ActeMedical acteMedical = ActeMedicalMapper.toEntity(acteMedicalCreatedDTO, rendezVous, serviceMedical);
        ActeMedical createdActeMedical = acteMedicalService.create(acteMedical);
        return Response.status(Response.Status.CREATED).entity(ActeMedicalMapper.toDTO(createdActeMedical)).build();
    }
    
    @GET
    @Path("/RendezVous/{id}")
    public List<ActeMedicalDTO> getActeMedicalsByRendezVousId(@PathParam("id") int rendezVousId){
        return acteMedicalService.findByRendezVousId(rendezVousId).stream().map(ActeMedicalMapper::toDTO).toList();
    }
}
