package com.example.mapper;

import com.example.dto.ServiceMedicalCreatedDTO;
import com.example.dto.ServiceMedicalDTO;
import com.example.entitys.ServiceMedical;

public class ServiceMedicalMapper {
    
    public static ServiceMedical toEntity(ServiceMedicalCreatedDTO dto) {
        ServiceMedical serviceMedical = new ServiceMedical();
        serviceMedical.setNomSM(dto.getNom());
        serviceMedical.setTypeSM(dto.getType());
        serviceMedical.setDescriptionSM(dto.getDescription());
        serviceMedical.setTarifSM(dto.getTarif());
        return serviceMedical;
    }
    
    public static ServiceMedicalDTO toDTO(ServiceMedical serviceMedical) {
        return new ServiceMedicalDTO(
            serviceMedical.getNumSM(),
            serviceMedical.getNomSM(),
            serviceMedical.getTypeSM(),
            serviceMedical.getDescriptionSM(),
            serviceMedical.getTarifSM()
        );
    }
}
