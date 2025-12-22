package com.example.mapper;

import com.example.dto.ActeMedicalCreatedDTO;
import com.example.dto.ActeMedicalDTO;
import com.example.entitys.ActeMedical;

public class ActeMedicalMapper {
    
    public static ActeMedical toEntity(ActeMedicalCreatedDTO dto) {
        ActeMedical acteMedical = new ActeMedical();
        acteMedical.setIdRv(dto.getIdRv());
        acteMedical.setNumSM(dto.getNumSM());
        acteMedical.setDescriptionAM(dto.getDescriptionAM());
        acteMedical.setTarrifAM(dto.getTarrifAM());
        return acteMedical;
    }
    
    public static ActeMedicalDTO toDTO(ActeMedical acteMedical) {
        return new ActeMedicalDTO(
            acteMedical.getIdAM(),
            acteMedical.getIdRv(),
            acteMedical.getNumSM(),
            acteMedical.getDescriptionAM(),
            acteMedical.getTarrifAM()
        );
    }
}
