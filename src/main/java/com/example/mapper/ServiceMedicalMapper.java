package com.example.mapper;

import com.example.dto.ServiceMedicalCreatedDTO;
import com.example.dto.ServiceMedicalDTO;
import com.example.entitys.ServiceMedical;

public class ServiceMedicalMapper {
    
    public static ServiceMedical toEntity(ServiceMedicalCreatedDTO dto) {
        ServiceMedical serviceMedical = new ServiceMedical();
        serviceMedical.setNomSM(dto.getNomSM());
        serviceMedical.setTypeSM(dto.getTypeSM());
        serviceMedical.setDescriptionSM(dto.getDescriptionSM());
        serviceMedical.setTarifSM(dto.getTarifSM());
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
