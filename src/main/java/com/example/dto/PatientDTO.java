//class for response dto
package com.example.dto;

import com.example.entitys.GroupSanguinP;
import com.example.entitys.SexeP;
import java.util.Date;

public class PatientDTO {
    private int idP;
    private String nomP;
    private String prenomP;
    private String emailP;
    private String mdpP;
    private Date dateNP;
    private String photoP;
    private GroupSanguinP groupeSanguinP;
    private SexeP sexeP;
    private String recouvrementP;

    public PatientDTO() {
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getPrenomP() {
        return prenomP;
    }

    public void setPrenomP(String prenomP) {
        this.prenomP = prenomP;
    }

    public String getEmailP() {
        return emailP;
    }

    public void setEmailP(String emailP) {
        this.emailP = emailP;
    }

    public String getMdpP() {
        return mdpP;
    }

    public void setMdpP(String mdpP) {
        this.mdpP = mdpP;
    }

    public Date getDateNP() {
        return dateNP;
    }

    public void setDateNP(Date dateNP) {
        this.dateNP = dateNP;
    }

    public String getPhotoP() {
        return photoP;
    }

    public void setPhotoP(String photoP) {
        this.photoP = photoP;
    }

    public GroupSanguinP getGroupeSanguinP() {
        return groupeSanguinP;
    }

    public void setGroupeSanguinP(GroupSanguinP groupeSanguinP) {
        this.groupeSanguinP = groupeSanguinP;
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
