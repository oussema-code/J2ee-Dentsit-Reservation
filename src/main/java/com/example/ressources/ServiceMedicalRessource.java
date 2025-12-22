package com.example.ressources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/servicemedicals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ServiceMedicalRessource {
    @Inject
    private ServiceMedicalService serviceMedicalService;
    @POST
    public Response createServiceMedical(ServiceMedical serviceMedical){
        ServiceMedical createdServiceMedical=serviceMedicalService.create(serviceMedical);
        return Response.status(Response.Status.CREATED).entity(createdServiceMedical).build();
    }
    @GET
    @Path("/{numSM}")
    public Response getServiceMedicalByNumSM(@PathParam("numSM") int numSM){
        ServiceMedical serviceMedical=serviceMedicalService.findByNumSM(numSM);
        if(serviceMedical==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(serviceMedical).build();
    }

    
}
