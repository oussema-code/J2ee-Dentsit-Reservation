//class for input dto
package com.example.dto;

import com.example.entitys.SexeP;
import com.example.entitys.GroupSanguinP;

public class PatientCreatedDTO {
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String dateNP;
    private String photoP;
    private GroupSanguinP groupSanguinP;
    private SexeP sexeP;
    private String recouvrementP;    
    public PatientCreatedDTO() {
    }
    public PatientCreatedDTO(String nom, String prenom, String email, String mdp, String dateNP, String photoP,
            GroupSanguinP groupSanguinP, SexeP sexeP, String recouvrementP) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.dateNP = dateNP;
        this.photoP = photoP;
        this.groupSanguinP = groupSanguinP;
        this.sexeP = sexeP;
        this.recouvrementP = recouvrementP;
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
    public String getDateNP() {
        return dateNP;
    }
    public void setDateNP(String dateNP) {
        this.dateNP = dateNP;
    }
    public String getPhotoP() {
        return photoP;
    }
    public void setPhotoP(String photoP) {
        this.photoP = photoP;
    }
    public GroupSanguinP getGroupSanguinP() {
        return groupSanguinP;
    }
    public void setGroupSanguinP(GroupSanguinP groupSanguinP) {
        this.groupSanguinP = groupSanguinP;
    }
    public SexeP getSexeP() {
        return sexeP;
    }
    public void setSexeP(SexeP sexeP) {
        this.sexeP = sexeP;
    }
    public String getRecouvrementP() {
        return recouvrementP;
    }
    public void setRecouvrementP(String recouvrementP) {
        this.recouvrementP = recouvrementP;
    }


}
