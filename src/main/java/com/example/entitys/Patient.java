package com.example.entitys;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 8)
    private int idP;

    @Column(nullable = false, length = 100)
    private String nomP;

    @Column(nullable = false, length = 100)
    private String prenomP;

    @Column(nullable = false, unique = true, length = 100)
    private String emailP;

    private Date dateNP;
    @Column(columnDefinition = "LONGTEXT")
    private String photoP;

    @Column(length = 2)
    private GroupSanguinP groupeSanguinP;

    @Column(length = 1)
    private SexeP sexeP;
    @Column(length = 10)


    private String mdpP;
    @Column(length = 100)
    private String recouvrementP;
    //relations
    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVous;

    // getters & setters
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
    public String getMdpP() {
        return mdpP;
    }
    public void setMdpP(String mdpP) {
        this.mdpP = mdpP;
    }
    public String getRecouvrementP() {
        return recouvrementP;
    }
    public void setRecouvrementP(String recouvrementP) {
        this.recouvrementP = recouvrementP;
    }
    public List<RendezVous> getRendezvous() {
        return rendezVous;
    }
    public void setRendezvous(List<RendezVous> rendezvous) {
        this.rendezVous = rendezvous;
    }
    
}
