package com.example.mapper;

import com.example.dto.DentisteCreatedDTO;
import com.example.dto.DentisteDTO;
import com.example.entitys.Dentiste;

public class DentisteMapper {
    
    public static Dentiste toEntity(DentisteCreatedDTO dto) {
        Dentiste dentiste = new Dentiste();
        dentiste.setNomD(dto.getNom());
        dentiste.setPrenomD(dto.getPrenom());
        dentiste.setEmailD(dto.getEmail());
        dentiste.setSpecialiteD(dto.getSpecialite());
        return dentiste;
    }
    
    public static DentisteDTO toDTO(Dentiste dentiste) {
        return new DentisteDTO(
            dentiste.getIdD(),
            dentiste.getNomD(),
            dentiste.getPrenomD(),
            dentiste.getEmailD(),
            dentiste.getSpecialiteD()
        );
    }
}
