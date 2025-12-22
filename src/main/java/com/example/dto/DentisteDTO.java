//class for response dto
package com.example.dto;

import com.example.entitys.SexeD;

public class DentisteDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String specialite;
    private String photo;
    private SexeD sexe;
    private int tel;

    public DentisteDTO() {
    }

    public DentisteDTO(int id, String nom, String prenom, String email, String specialite, String photo,
            SexeD sexe, int tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.specialite = specialite;
        this.photo = photo;
        this.sexe = sexe;
        this.tel = tel;
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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public SexeD getSexe() {
        return sexe;
    }

    public void setSexe(SexeD sexe) {
        this.sexe = sexe;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
