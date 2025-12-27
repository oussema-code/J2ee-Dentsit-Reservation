package com.example.ressources;

import java.util.List;
import com.example.dto.AideSoignantCreatedDTO;
import com.example.dto.AideSoignantDTO;
import com.example.entitys.AideSoignant;
import com.example.mapper.AideSoignantMapper;
import com.example.service.AideSoignantService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/aidesoignants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AideSoignantRessource {
    @Inject
    private AideSoignantService aideSoignantService;
    
    @POST
    public Response createAideSoignant(AideSoignantCreatedDTO aideSoignantCreatedDTO){
        AideSoignant aideSoignant = AideSoignantMapper.toEntity(aideSoignantCreatedDTO);
        AideSoignant createdAideSoignant = aideSoignantService.create(aideSoignant);
        return Response.status(Response.Status.CREATED).entity(AideSoignantMapper.toDTO(createdAideSoignant)).build();
    }
    
    @GET
    public List<AideSoignantDTO> getAllAideSoignants(){
        return aideSoignantService.findAll().stream().map(AideSoignantMapper::toDTO).toList();
    }
    
    @GET
    @Path("/{id}")
    public Response getAideSoignantById(@PathParam("id") int id){
        AideSoignant aideSoignant = aideSoignantService.findById(id);
        if(aideSoignant == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(AideSoignantMapper.toDTO(aideSoignant)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteAideSoignant(@PathParam("id") int id){
        AideSoignant aideSoignant = aideSoignantService.findById(id);
        if(aideSoignant == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        aideSoignantService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateAideSoignant(@PathParam("id") int id, AideSoignant aideSoignant){
        AideSoignant existingAideSoignant = aideSoignantService.findById(id);
        if(existingAideSoignant == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        aideSoignant.setIdAS(id);
        AideSoignant updatedAideSoignant = aideSoignantService.update(aideSoignant);
        return Response.ok(updatedAideSoignant).build();
    }
}
