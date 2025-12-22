//class for input dto
package com.example.dto;

public class ServiceMedicalCreatedDTO {
    private String nomSM;
    private String typeSM;
    private String descriptionSM;
    private float tarifSM;

    public ServiceMedicalCreatedDTO() {
    }

    public ServiceMedicalCreatedDTO(String nomSM, String typeSM, String descriptionSM, float tarifSM) {
        this.nomSM = nomSM;
        this.typeSM = typeSM;
        this.descriptionSM = descriptionSM;
        this.tarifSM = tarifSM;
    }

    public String getNomSM() {
        return nomSM;
    }

    public void setNomSM(String nomSM) {
        this.nomSM = nomSM;
    }

    public String getTypeSM() {
        return typeSM;
    }

    public void setTypeSM(String typeSM) {
        this.typeSM = typeSM;
    }

    public String getDescriptionSM() {
        return descriptionSM;
    }

    public void setDescriptionSM(String descriptionSM) {
        this.descriptionSM = descriptionSM;
    }

    public float getTarifSM() {
        return tarifSM;
    }

    public void setTarifSM(float tarifSM) {
        this.tarifSM = tarifSM;
    }
}
