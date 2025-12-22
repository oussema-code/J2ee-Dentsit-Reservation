package com.example.ressources;

import com.example.dto.ServiceMedicalCreatedDTO;
import com.example.dto.ServiceMedicalDTO;
import com.example.entitys.ServiceMedical;
import com.example.mapper.ServiceMedicalMapper;
import com.example.service.ServiceMedicalService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/servicemedicals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceMedicalRessource {
    @Inject
    private ServiceMedicalService serviceMedicalService;
    
    @POST
    public Response createServiceMedical(ServiceMedicalCreatedDTO serviceMedicalCreatedDTO){
        ServiceMedical serviceMedical = ServiceMedicalMapper.toEntity(serviceMedicalCreatedDTO);
        ServiceMedical createdServiceMedical = serviceMedicalService.create(serviceMedical);
        return Response.status(Response.Status.CREATED).entity(ServiceMedicalMapper.toDTO(createdServiceMedical)).build();
    }
    
    @GET
    @Path("/{numSM}")
    public Response getServiceMedicalByNumSM(@PathParam("numSM") int numSM){
        ServiceMedical serviceMedical = serviceMedicalService.findByNumSM(numSM);
        if(serviceMedical == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ServiceMedicalMapper.toDTO(serviceMedical)).build();
    }
}
