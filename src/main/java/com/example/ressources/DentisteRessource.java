package com.example.ressources;

import com.example.entitys.Dentiste;
import com.example.service.DentisteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/dentistes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DentisteRessource {
    @Inject
    private DentisteService dentisteService;
    @POST
    public Response createDentiste(Dentiste dentiste){
        Dentiste createdDentiste=dentisteService.create(dentiste);
        return Response.status(Response.Status.CREATED).entity(createdDentiste).build();
    }
    @GET
    public Response getAllDentistes(){
        if(dentisteService.findAll().isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(dentisteService.findAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getDentisteById(@PathParam("id") int id){
        Dentiste dentiste=dentisteService.findById(id);
        if(dentiste==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dentiste).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteDentiste(@PathParam("id") int id){
        Dentise dentiste=dentisteService.findById(id);
        if(dentiste==null){
            return Response.status(Response.Status.NOT_FOUND).build();
    }
    dentisteService.delete(id);
    return Response.status(Response.Status.NO_CONTENT).build();
    }
    @PUT
    @Path("/{id}")
    public Response updatedDentiste(@PathParam("id") int id,Dentiste dentiste){
        Dentiste existingDentiste=dentisteService.findById(id);
        if(existingDentiste==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dentiste.setIdD(id);
        Dentiste updatedDentiste=dentisteService.update(existingDentiste);
        return Response.ok(updatedDentiste).build();

    }



}

    

