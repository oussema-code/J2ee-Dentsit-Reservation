package com.example.ressources;

import com.example.dto.DentisteCreatedDTO;
import com.example.dto.DentisteDTO;
import com.example.entitys.Dentiste;
import com.example.mapper.DentisteMapper;
import com.example.service.DentisteService;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/dentistes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DentisteRessource {
    @Inject
    private DentisteService dentisteService;
    
    @POST
    public Response createDentiste(DentisteCreatedDTO dentisteCreatedDTO){
        Dentiste dentiste = DentisteMapper.toEntity(dentisteCreatedDTO);
        Dentiste createdDentiste = dentisteService.create(dentiste);
        return Response.status(Response.Status.CREATED).entity(DentisteMapper.toDTO(createdDentiste)).build();
    }
    
    @GET
    public List<DentisteDTO> getAllDentistes(){
        return dentisteService.findAll().stream().map(DentisteMapper::toDTO).toList();
    }
    
    @GET
    @Path("/{id}")
    public Response getDentisteById(@PathParam("id") int id){
        Dentiste dentiste = dentisteService.findById(id);
        if(dentiste == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(DentisteMapper.toDTO(dentiste)).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteDentiste(@PathParam("id") int id){
        Dentiste dentiste = dentisteService.findById(id);
        if(dentiste == null){
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

    

