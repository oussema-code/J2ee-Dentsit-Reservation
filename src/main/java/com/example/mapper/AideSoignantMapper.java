package com.example.mapper;

import com.example.dto.AideSoignantCreatedDTO;
import com.example.dto.AideSoignantDTO;
import com.example.entitys.AideSoignant;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AideSoignantMapper {
    
    public static AideSoignant toEntity(AideSoignantCreatedDTO dto) {
        AideSoignant aideSoignant = new AideSoignant();
        aideSoignant.setNomAS(dto.getNomAS());
        aideSoignant.setPrenomAS(dto.getPrenomAS());
        aideSoignant.setAdresseAS(dto.getAdresseAS());
        aideSoignant.setTelephoneAS(dto.getTelephoneAS());
        aideSoignant.setEmailAS(dto.getEmailAS());
        aideSoignant.setDiplomeAS(dto.getDiplomeAS());
        aideSoignant.setPhotoAS(dto.getPhotoAS());
        aideSoignant.setSexeAS(dto.getSexeAS());
        
        // Hash password with SHA-256
        if (dto.getMdpAS() != null && !dto.getMdpAS().isEmpty()) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(dto.getMdpAS().getBytes());
                String hashedPassword = Base64.getEncoder().encodeToString(hash);
                aideSoignant.setMdpAS(hashedPassword);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error hashing password", e);
            }
        }
        
        if (dto.getDateNAS() != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                aideSoignant.setDateNAS(dateFormat.parse(dto.getDateNAS()));
            } catch (ParseException e) {
                // Handle parse exception
            }
        }
        
        return aideSoignant;
    }
    
    public static AideSoignantDTO toDTO(AideSoignant aideSoignant) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        AideSoignantDTO dto = new AideSoignantDTO(
            aideSoignant.getIdAS(),
            aideSoignant.getNomAS(),
            aideSoignant.getPrenomAS(),
            aideSoignant.getAdresseAS(),
            aideSoignant.getTelephoneAS(),
            aideSoignant.getEmailAS(),
            aideSoignant.getDateNAS() != null ? dateFormat.format(aideSoignant.getDateNAS()) : null,
            aideSoignant.getDiplomeAS(),
            aideSoignant.getPhotoAS(),
            aideSoignant.getSexeAS()
        );
        dto.setMdpAS(aideSoignant.getMdpAS());
        return dto;
    }
}
