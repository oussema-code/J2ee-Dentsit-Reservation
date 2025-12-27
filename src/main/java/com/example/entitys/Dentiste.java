package com.example.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.List;

import com.example.entitys.*;

import jakarta.persistence.*;

@Entity
@Table(name = "dentiste")
public class Dentiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 8)
    private int idD;
    @Column(nullable = false, length = 100) 
    private String nomD;
    @Column(nullable = false, length = 100)
    private String prenomD;
    @Column(nullable = false, unique = true, length = 100)
    private String emailD;
    @Column(length = 10)
    private String mdpD;
    @Column(length = 100)
    private String specialiteD;
    @Column(length = 100)
    private String photoD;
    @Column(length = 1)
    private SexeD sexeD;
    @Column(length = 8)
    private int telD;
    //relations
    @OneToMany(mappedBy = "dentiste")
    private List<RendezVous> rendezVous;
    // getters & setters
    public int getIdD() {
        return idD; 
    }
    public void setIdD(int idD) {
        this.idD = idD;
    }
    public String getNomD() {
        return nomD;
    }
    public void setNomD(String nomD) {
        this.nomD = nomD;
    }
    public String getPrenomD() {
        return prenomD;
    }
    public void setPrenomD(String prenomD) {
        this.prenomD = prenomD;
    }
    public String getEmailD() {
        return emailD;
    }
    public void setEmailD(String emailD) {
        this.emailD = emailD;
    }
    public String getMdpD() {
        return mdpD;
    }
    public void setMdpD(String mdpD) {
        this.mdpD = mdpD;
    }
    public String getSpecialiteD() {
        return specialiteD;
    }
    public void setSpecialiteD(String specialiteD) {
        this.specialiteD = specialiteD;
    }
    public String getPhotoD() {
        return photoD;
    }
    public void setPhotoD(String photoD) {
        this.photoD = photoD;
    }
    public SexeD getSexeD() {
        return sexeD;
    }
    public void setSexeD(SexeD sexeD) {
        this.sexeD = sexeD;
    }
    public int getTelD() {
        return telD;
    }
    public void setTelD(int telD) {
        this.telD = telD;
    }
    public List<RendezVous> getRendezvous() {
        return rendezVous;
    }
    public void setRendezvous(List<RendezVous> rendezvous) {
        this.rendezVous = rendezvous;
    }

    


}
