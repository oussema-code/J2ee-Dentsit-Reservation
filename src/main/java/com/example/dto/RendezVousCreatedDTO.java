//class for input dto
package com.example.dto;

public class RendezVousCreatedDTO {
    private int idP;
    private int idD;
    private String dateRv;
    private String heureRv;
    private String statutRv;
    private String descriptionRv;

    public RendezVousCreatedDTO() {
    }

    public RendezVousCreatedDTO(int idP, int idD, String dateRv, String heureRv, String statutRv, String descriptionRv) {
        this.idP = idP;
        this.idD = idD;
        this.dateRv = dateRv;
        this.heureRv = heureRv;
        this.statutRv = statutRv;
        this.descriptionRv = descriptionRv;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }

    public String getDateRv() {
        return dateRv;
    }

    public void setDateRv(String dateRv) {
        this.dateRv = dateRv;
    }

    public String getHeureRv() {
        return heureRv;
    }

    public void setHeureRv(String heureRv) {
        this.heureRv = heureRv;
    }

    public String getStatutRv() {
        return statutRv;
    }

    public void setStatutRv(String statutRv) {
        this.statutRv = statutRv;
    }

    public String getDescriptionRv() {
        return descriptionRv;
    }

    public void setDescriptionRv(String descriptionRv) {
        this.descriptionRv = descriptionRv;
    }
}
