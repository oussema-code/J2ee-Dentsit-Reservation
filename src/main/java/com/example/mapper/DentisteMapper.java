package com.example.mapper;

import com.example.dto.DentisteCreatedDTO;
import com.example.dto.DentisteDTO;
import com.example.entitys.Dentiste;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DentisteMapper {
    
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    public static Dentiste toEntity(DentisteCreatedDTO dto) {
        Dentiste dentiste = new Dentiste();
        dentiste.setNomD(dto.getNom());
        dentiste.setPrenomD(dto.getPrenom());
        dentiste.setEmailD(dto.getEmail());
        dentiste.setMdpD(hashPassword(dto.getMdp()));
        dentiste.setSpecialiteD(dto.getSpecialite());
        dentiste.setPhotoD(dto.getPhoto());
        dentiste.setSexeD(dto.getSexe());
        dentiste.setTelD(dto.getTel());
        return dentiste;
    }
    
    public static DentisteDTO toDTO(Dentiste dentiste) {
        return new DentisteDTO(
            dentiste.getIdD(),
            dentiste.getNomD(),
            dentiste.getPrenomD(),
            dentiste.getEmailD(),
            dentiste.getSpecialiteD(),
            dentiste.getPhotoD(),
            dentiste.getSexeD(),
            dentiste.getTelD()
        );
    }
}
