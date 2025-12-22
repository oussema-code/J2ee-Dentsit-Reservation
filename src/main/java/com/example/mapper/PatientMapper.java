package com.example.mapper;

import com.example.dto.PatientCreatedDTO;
import com.example.dto.PatientDTO;
import com.example.entitys.Patient;

public class PatientMapper {
    
    public static Patient toEntity(PatientCreatedDTO dto) {
        Patient patient = new Patient();
        patient.setNomP(dto.getNom());
        patient.setPrenomP(dto.getPrenom());
        patient.setEmailP(dto.getEmail());
        return patient;
    }
    
    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
            patient.getIdP(),
            patient.getNomP(),
            patient.getPrenomP(),
            patient.getEmailP()
        );
    }
}
