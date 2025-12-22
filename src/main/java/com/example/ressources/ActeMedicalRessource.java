package com.example.ressources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/actemedicals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ActeMedicalRessource {
    @Inject
    private ActeMedicalService acteMedicalService;
    @POST
    public Response createActeMedical(ActeMedical acteMedical){
        ActeMedical createdActeMedical=acteMedicalService.create(acteMedical);
        return Response.status(Response.Status.CREATED).entity(createdActeMedical).build();
    }
    @GET
    @Path("/RendezVous/{id}")
    public Response getActeMedicalsByRendezVousId(@PathParam("id") int rendezVousId){
        List<ActeMedical> acteMedicalList=acteMedicalService.findByRendezVousId(rendezVousId);
        if(acteMedicalList.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(acteMedicalList).build();

    }
    
}
