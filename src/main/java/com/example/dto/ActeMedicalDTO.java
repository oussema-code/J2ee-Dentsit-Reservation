//class for response dto
package com.example.dto;

public class ActeMedicalDTO {
    private int id;
    private int idRendezVous;
    private int idServiceMedical;
    private String notes;

    public ActeMedicalDTO() {
    }

    public ActeMedicalDTO(int id, int idRendezVous, int idServiceMedical, String notes) {
        this.id = id;
        this.idRendezVous = idRendezVous;
        this.idServiceMedical = idServiceMedical;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public int getIdServiceMedical() {
        return idServiceMedical;
    }

    public void setIdServiceMedical(int idServiceMedical) {
        this.idServiceMedical = idServiceMedical;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
