package com.example.mapper;

import com.example.dto.ActeMedicalCreatedDTO;
import com.example.dto.ActeMedicalDTO;
import com.example.entitys.ActeMedical;
import com.example.entitys.RendezVous;
import com.example.entitys.ServiceMedical;

public class ActeMedicalMapper {
    
    public static ActeMedical toEntity(ActeMedicalCreatedDTO dto, RendezVous rendezVous, ServiceMedical serviceMedical) {
        ActeMedical acteMedical = new ActeMedical();
        acteMedical.setRendezVous(rendezVous);
        acteMedical.setServiceMedical(serviceMedical);
        acteMedical.setDescriptionAM(dto.getDescriptionAM());
        acteMedical.setTarrifAM(dto.getTarrifAM());
        return acteMedical;
    }
    
    public static ActeMedicalDTO toDTO(ActeMedical acteMedical) {
        return new ActeMedicalDTO(
            acteMedical.getIdAM(),
            acteMedical.getRendezVous() != null ? acteMedical.getRendezVous().getIdRv() : 0,
            acteMedical.getServiceMedical() != null ? acteMedical.getServiceMedical().getNumSM() : 0,
            acteMedical.getDescriptionAM(),
            acteMedical.getTarrifAM()
        );
    }
}
