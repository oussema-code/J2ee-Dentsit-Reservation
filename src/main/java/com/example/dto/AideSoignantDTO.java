package com.example.dto;

public class AideSoignantDTO {
    private int idAS;
    private String nomAS;
    private String prenomAS;
    private String adresseAS;
    private String telephoneAS;
    private String emailAS;
    private String mdpAS;
    private String dateNAS;
    private String diplomeAS;
    private String photoAS;
    private String sexeAS;
    
    public AideSoignantDTO(int idAS, String nomAS, String prenomAS, String adresseAS, 
                          String telephoneAS, String emailAS, String dateNAS, 
                          String diplomeAS, String photoAS, String sexeAS) {
        this.idAS = idAS;
        this.nomAS = nomAS;
        this.prenomAS = prenomAS;
        this.adresseAS = adresseAS;
        this.telephoneAS = telephoneAS;
        this.emailAS = emailAS;
        this.dateNAS = dateNAS;
        this.diplomeAS = diplomeAS;
        this.photoAS = photoAS;
        this.sexeAS = sexeAS;
    }
    
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
    
    public String getDateNAS() {
        return dateNAS;
    }
    
    public void setDateNAS(String dateNAS) {
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
}
