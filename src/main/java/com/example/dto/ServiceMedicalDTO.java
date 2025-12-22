//class for response dto
package com.example.dto;

public class ServiceMedicalDTO {
    private int id;
    private String nom;
    private String type;
    private String description;
    private float tarif;

    public ServiceMedicalDTO() {
    }

    public ServiceMedicalDTO(int id, String nom, String type, String description, float tarif) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.tarif = tarif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }
}
