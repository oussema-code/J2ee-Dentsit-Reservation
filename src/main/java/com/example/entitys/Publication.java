package com.example.entitys;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPub;
    
    @Column(nullable = false, length = 255)
    private String titrePub;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePublicationPub;
    
    @Column(columnDefinition = "LONGTEXT")
    private String affichePub;
    
    @Column(nullable = false, length = 100)
    private String typePublicationPub;
    
    @Column(columnDefinition = "TEXT")
    private String resumePub;
    
    @ManyToOne
    @JoinColumn(name = "idAS", nullable = false)
    private AideSoignant aideSoignant;
    
    // Getters and Setters
    public int getIdPub() {
        return idPub;
    }
    
    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }
    
    public String getTitrePub() {
        return titrePub;
    }
    
    public void setTitrePub(String titrePub) {
        this.titrePub = titrePub;
    }
    
    public Date getDatePublicationPub() {
        return datePublicationPub;
    }
    
    public void setDatePublicationPub(Date datePublicationPub) {
        this.datePublicationPub = datePublicationPub;
    }
    
    public String getAffichePub() {
        return affichePub;
    }
    
    public void setAffichePub(String affichePub) {
        this.affichePub = affichePub;
    }
    
    public String getTypePublicationPub() {
        return typePublicationPub;
    }
    
    public void setTypePublicationPub(String typePublicationPub) {
        this.typePublicationPub = typePublicationPub;
    }
    
    public String getResumePub() {
        return resumePub;
    }
    
    public void setResumePub(String resumePub) {
        this.resumePub = resumePub;
    }
    
    public AideSoignant getAideSoignant() {
        return aideSoignant;
    }
    
    public void setAideSoignant(AideSoignant aideSoignant) {
        this.aideSoignant = aideSoignant;
    }
}
