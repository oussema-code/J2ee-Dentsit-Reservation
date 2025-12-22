package com.example.ressources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/rendezvous")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RendezVousRessource {
    @Inject
    private RendezVousService rendezVousService;
    @POST
    public Response createRendezVous(RendezVous rendezVous){
        RendezVous createdRendezVous=rendezVousService.create(rendezVous);
        return Response.status(Response.Status.CREATED).entity(createdRendezVous).build();
    }
    @GET
    @Path("/patient/{id}")
    public Response getRendezVousByPatientId(@PathParam("id") int patientId){
        List<RendezVous> rendezVousList=rendezVousService.findByPatientId(patientId);
        if(rendezVousList.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(rendezVousList).build();
    }
    @GET
    @Path("/dentiste/{id}")
    public Response getRendezVousByDentisteId(@PathParam("id") int dentisteId){
        List<RendezVous> rendezVousList=rendezVousService.findByDentisteId(dentisteId);
        if(rendezVousList.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(rendezVousList).build();
    }
}