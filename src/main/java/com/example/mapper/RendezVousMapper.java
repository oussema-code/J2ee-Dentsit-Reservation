package com.example.mapper;

import com.example.dto.RendezVousCreatedDTO;
import com.example.dto.RendezVousDTO;
import com.example.entitys.RendezVous;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Time;
import java.util.Date;

public class RendezVousMapper {
    
    public static RendezVous toEntity(RendezVousCreatedDTO dto) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setIdP(dto.getIdP());
        rendezVous.setIdD(dto.getIdD());
        rendezVous.setStatutRv(dto.getStatutRv());
        rendezVous.setDescriptionRv(dto.getDescriptionRv());
        
        if (dto.getDateRv() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                rendezVous.setDateRv(dateFormat.parse(dto.getDateRv()));
            } catch (ParseException e) {
                // Handle parse exception
            }
        }
        
        if (dto.getHeureRv() != null) {
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                rendezVous.setHeureRv(new Time(timeFormat.parse(dto.getHeureRv()).getTime()));
            } catch (ParseException e) {
                // Handle parse exception
            }
        }
        
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
