package com.example.entitys;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "aide_soignant")
public class AideSoignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAS;
    
    @Column(nullable = false, length = 100)
    private String nomAS;
    
    @Column(nullable = false, length = 100)
    private String prenomAS;
    
    @Column(length = 255)
    private String adresseAS;
    
    @Column(length = 20)
    private String telephoneAS;
    
    @Column(nullable = false, unique = true, length = 100)
    private String emailAS;
    
    @Column(nullable = false, length = 255)
    private String mdpAS;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateNAS;
    
    @Column(length = 150)
    private String diplomeAS;
    
    @Column(columnDefinition = "LONGTEXT")
    private String photoAS;
    
    @Column(nullable = false, length = 1)
    private String sexeAS;
    
    @OneToMany(mappedBy = "aideSoignant")
    private List<Publication> publications;
    
    // Getters and Setters
    public int getIdAS() {
        return idAS;
    }
    
    public void setIdAS(int idAS) {
        this.idAS = idAS;
    }
    
    public String getNomAS() {
        return nomAS;
    }
    
    public void setNomAS(String nomAS) {
        this.nomAS = nomAS;
    }
    
    public String getPrenomAS() {
        return prenomAS;
    }
    
    public void setPrenomAS(String prenomAS) {
        this.prenomAS = prenomAS;
    }
    
    public String getAdresseAS() {
        return adresseAS;
    }
    
    public void setAdresseAS(String adresseAS) {
        this.adresseAS = adresseAS;
    }
    
    public String getTelephoneAS() {
        return telephoneAS;
    }
    
    public void setTelephoneAS(String telephoneAS) {
        this.telephoneAS = telephoneAS;
    }
    
    public String getEmailAS() {
        return emailAS;
    }
    
    public void setEmailAS(String emailAS) {
        this.emailAS = emailAS;
    }
    
    public String getMdpAS() {
        return mdpAS;
    }
    
    public void setMdpAS(String mdpAS) {
        this.mdpAS = mdpAS;
    }
    
    public Date getDateNAS() {
        return dateNAS;
    }
    
    public void setDateNAS(Date dateNAS) {
        this.dateNAS = dateNAS;
    }
    
    public String getDiplomeAS() {
        return diplomeAS;
    }
    
    public void setDiplomeAS(String diplomeAS) {
        this.diplomeAS = diplomeAS;
    }
    
    public String getPhotoAS() {
        return photoAS;
    }
    
    public void setPhotoAS(String photoAS) {
        this.photoAS = photoAS;
    }
    
    public String getSexeAS() {
        return sexeAS;
    }
    
    public void setSexeAS(String sexeAS) {
        this.sexeAS = sexeAS;
    }
    
    public List<Publication> getPublications() {
        return publications;
    }
    
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
