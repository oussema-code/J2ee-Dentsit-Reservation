package com.example.dto;

public class PublicationDTO {
    private int idPub;
    private String titrePub;
    private String datePublicationPub;
    private String affichePub;
    private String typePublicationPub;
    private String resumePub;
    private int idAS;
    
    public PublicationDTO(int idPub, String titrePub, String datePublicationPub, 
                         String affichePub, String typePublicationPub, 
                         String resumePub, int idAS) {
        this.idPub = idPub;
        this.titrePub = titrePub;
        this.datePublicationPub = datePublicationPub;
        this.affichePub = affichePub;
        this.typePublicationPub = typePublicationPub;
        this.resumePub = resumePub;
        this.idAS = idAS;
    }
    
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
    
    public String getDatePublicationPub() {
        return datePublicationPub;
    }
    
    public void setDatePublicationPub(String datePublicationPub) {
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
    
    public int getIdAS() {
        return idAS;
    }
    
    public void setIdAS(int idAS) {
        this.idAS = idAS;
    }
}
