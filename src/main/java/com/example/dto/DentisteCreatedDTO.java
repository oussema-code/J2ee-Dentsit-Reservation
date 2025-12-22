//class for input dto
package com.example.dto;

import com.example.entitys.SexeD;

public class DentisteCreatedDTO {
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String specialite;
    private String photo;
    private SexeD sexe;
    private int tel;

    public DentisteCreatedDTO() {
    }

    public DentisteCreatedDTO(String nom, String prenom, String email, String mdp, String specialite, String photo,
            SexeD sexe, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.specialite = specialite;
        this.photo = photo;
        this.sexe = sexe;
        this.tel = tel;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
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
