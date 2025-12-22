//class for response dto
package com.example.dto;

public class RendezVousDTO {
    private int id;
    private int idPatient;
    private int idDentiste;
    private String date;
    private String heure;
    private String statut;
    private String description;

    public RendezVousDTO() {
    }

    public RendezVousDTO(int id, int idPatient, int idDentiste, String date, String heure, String statut, String description) {
        this.id = id;
        this.idPatient = idPatient;
        this.idDentiste = idDentiste;
        this.date = date;
        this.heure = heure;
        this.statut = statut;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdDentiste() {
        return idDentiste;
    }

    public void setIdDentiste(int idDentiste) {
        this.idDentiste = idDentiste;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
