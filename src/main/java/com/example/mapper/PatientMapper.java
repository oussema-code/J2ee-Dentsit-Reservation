package com.example.mapper;

import com.example.dto.PatientCreatedDTO;
import com.example.dto.PatientDTO;
import com.example.entitys.Patient;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PatientMapper {
    
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    public static Patient toEntity(PatientCreatedDTO dto) {
        Patient patient = new Patient();
        patient.setNomP(dto.getNom());
        patient.setPrenomP(dto.getPrenom());
        patient.setEmailP(dto.getEmail());
        patient.setMdpP(hashPassword(dto.getMdp()));
        patient.setPhotoP(dto.getPhotoP());
        patient.setGroupeSanguinP(dto.getGroupSanguinP());
        patient.setSexeP(dto.getSexeP());
        patient.setRecouvrementP(dto.getRecouvrementP());
        
        if (dto.getDateNP() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                patient.setDateNP(dateFormat.parse(dto.getDateNP()));
            } catch (ParseException e) {
                // Handle parse exception
            }
        }
        return patient;
    }
    
    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
            patient.getIdP(),
            patient.getNomP(),
            patient.getPrenomP(),
            patient.getEmailP(),
           
        );
    }
}
