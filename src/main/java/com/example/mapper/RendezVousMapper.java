package com.example.mapper;

import com.example.dto.RendezVousCreatedDTO;
import com.example.dto.RendezVousDTO;
import com.example.entitys.RendezVous;
import com.example.entitys.Patient;
import com.example.entitys.Dentiste;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Time;
import java.util.Date;

public class RendezVousMapper {
    
    public static RendezVous toEntity(RendezVousCreatedDTO dto, Patient patient, Dentiste dentiste) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setPatient(patient);
        rendezVous.setDentiste(dentiste);
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
                // Accept both HH:mm and HH:mm:ss formats
                String timeStr = dto.getHeureRv();
                if (!timeStr.contains(":")) {
                    throw new ParseException("Invalid time format", 0);
                }
                // Add seconds if not present
                if (timeStr.split(":").length == 2) {
                    timeStr += ":00";
                }
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                rendezVous.setHeureRv(new Time(timeFormat.parse(timeStr).getTime()));
            } catch (ParseException e) {
                // Handle parse exception
                e.printStackTrace();
            }
        }
        
        return rendezVous;
    }
    
    public static RendezVousDTO toDTO(RendezVous rendezVous) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        return new RendezVousDTO(
            rendezVous.getIdRv(),
            rendezVous.getPatient().getIdP(),
            rendezVous.getDentiste().getIdD(),
            rendezVous.getPatient().getNomP(),
            rendezVous.getPatient().getPrenomP(),
            rendezVous.getDentiste().getNomD(),
            rendezVous.getDentiste().getPrenomD(),
            rendezVous.getDateRv() != null ? dateFormat.format(rendezVous.getDateRv()) : null,
            rendezVous.getHeureRv() != null ? timeFormat.format(rendezVous.getHeureRv()) : null,
            rendezVous.getStatutRv(),
            rendezVous.getDescriptionRv()
        );
    }
}
