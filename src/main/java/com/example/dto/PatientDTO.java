//class for response dto
package com.example.dto;

import com.example.entitys.SexeP;

public class PatientDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String photoP;
    private SexeP sexeP;

    public PatientDTO() {
    }

    public PatientDTO(int id, String nom, String prenom, String email, String photoP, SexeP sexeP) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.photoP = photoP;
        this.sexeP = sexeP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoP() {
        return photoP;
    }

    public void setPhotoP(String photoP) {
        this.photoP = photoP;
    }

    public SexeP getSexeP() {
        return sexeP;
    }

    public void setSexeP(SexeP sexeP) {
        this.sexeP = sexeP;
    }
}
