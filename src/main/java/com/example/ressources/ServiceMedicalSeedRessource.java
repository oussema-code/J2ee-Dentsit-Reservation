package com.example.ressources;

import com.example.service.ServiceMedicalSeeder;
import jakarta.ejb.EJB;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST endpoint to seed the database with dental services
 * Call: POST /api/servicemedicals/seed
 */
@Path("/servicemedicals")
public class ServiceMedicalSeedRessource {

    @EJB
    private ServiceMedicalSeeder seeder;

    @POST
    @Path("/seed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response seedDatabase() {
        try {
            int count = seeder.seedServices();
            return Response.ok()
                    .entity("{\"message\": \"" + count + " services inserted successfully\", \"count\": " + count + "}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
