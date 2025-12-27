package com.example.ressources;

import java.util.List;
import com.example.dto.PublicationCreatedDTO;
import com.example.dto.PublicationDTO;
import com.example.entitys.Publication;
import com.example.entitys.AideSoignant;
import com.example.mapper.PublicationMapper;
import com.example.service.PublicationService;
import com.example.service.AideSoignantService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/publications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicationRessource {
    @Inject
    private PublicationService publicationService;
    
    @Inject
    private AideSoignantService aideSoignantService;
    
    @POST
    public Response createPublication(PublicationCreatedDTO publicationCreatedDTO){
        AideSoignant aideSoignant = aideSoignantService.findById(publicationCreatedDTO.getIdAS());
        if(aideSoignant == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Aide-soignant not found").build();
        }
        Publication publication = PublicationMapper.toEntity(publicationCreatedDTO, aideSoignant);
        Publication createdPublication = publicationService.create(publication);
        return Response.status(Response.Status.CREATED).entity(PublicationMapper.toDTO(createdPublication)).build();
    }
    
    @GET
    public List<PublicationDTO> getAllPublications(){
        return publicationService.findAll().stream().map(PublicationMapper::toDTO).toList();
    }
    
    @GET
    @Path("/{id}")
    public Response getPublicationById(@PathParam("id") int id){
        Publication publication = publicationService.findById(id);
        if(publication == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(PublicationMapper.toDTO(publication)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deletePublication(@PathParam("id") int id){
        Publication publication = publicationService.findById(id);
        if(publication == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        publicationService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updatePublication(@PathParam("id") int id, PublicationCreatedDTO publicationCreatedDTO){
        Publication existingPublication = publicationService.findById(id);
        if(existingPublication == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AideSoignant aideSoignant = aideSoignantService.findById(publicationCreatedDTO.getIdAS());
        if(aideSoignant == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Aide-soignant not found").build();
        }
        Publication publication = PublicationMapper.toEntity(publicationCreatedDTO, aideSoignant);
        publication.setIdPub(id);
        Publication updatedPublication = publicationService.update(publication);
        return Response.ok(updatedPublication).build();
    }
}
