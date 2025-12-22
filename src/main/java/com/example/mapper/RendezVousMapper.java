package com.example.mapper;

import com.example.dto.RendezVousCreatedDTO;
import com.example.dto.RendezVousDTO;
import com.example.entitys.RendezVous;
import java.text.SimpleDateFormat;

public class RendezVousMapper {
    
    public static RendezVous toEntity(RendezVousCreatedDTO dto) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setIdP(dto.getIdPatient());
        rendezVous.setIdD(dto.getIdDentiste());
        rendezVous.setStatutRv(dto.getStatut());
        rendezVous.setDescriptionRv(dto.getDescription());
        return rendezVous;
    }
    
    public static RendezVousDTO toDTO(RendezVous rendezVous) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        return new RendezVousDTO(
            rendezVous.getIdRv(),
            rendezVous.getIdP(),
            rendezVous.getIdD(),
            rendezVous.getDateRv() != null ? dateFormat.format(rendezVous.getDateRv()) : null,
            rendezVous.getHeureRv() != null ? timeFormat.format(rendezVous.getHeureRv()) : null,
            rendezVous.getStatutRv(),
            rendezVous.getDescriptionRv()
        );
    }
}
