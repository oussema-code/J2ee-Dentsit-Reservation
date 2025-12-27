package com.example.mapper;

import com.example.dto.PublicationCreatedDTO;
import com.example.dto.PublicationDTO;
import com.example.entitys.Publication;
import com.example.entitys.AideSoignant;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class PublicationMapper {
    
    public static Publication toEntity(PublicationCreatedDTO dto, AideSoignant aideSoignant) {
        Publication publication = new Publication();
        publication.setTitrePub(dto.getTitrePub());
        publication.setAffichePub(dto.getAffichePub());
        publication.setTypePublicationPub(dto.getTypePublicationPub());
        publication.setResumePub(dto.getResumePub());
        publication.setAideSoignant(aideSoignant);
        
        if (dto.getDatePublicationPub() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                publication.setDatePublicationPub(dateFormat.parse(dto.getDatePublicationPub()));
            } catch (ParseException e) {
                // Handle parse exception
            }
        }
        
        return publication;
    }
    
    public static PublicationDTO toDTO(Publication publication) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        return new PublicationDTO(
            publication.getIdPub(),
            publication.getTitrePub(),
            publication.getDatePublicationPub() != null ? dateFormat.format(publication.getDatePublicationPub()) : null,
            publication.getAffichePub(),
            publication.getTypePublicationPub(),
            publication.getResumePub(),
            publication.getAideSoignant().getIdAS()
        );
    }
}
