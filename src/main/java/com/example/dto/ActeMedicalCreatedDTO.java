//class for input dto
package com.example.dto;

public class ActeMedicalCreatedDTO {
    private int idRv;
    private int numSM;
    private String descriptionAM;
    private float tarrifAM;

    public ActeMedicalCreatedDTO() {
    }

    public ActeMedicalCreatedDTO(int idRv, int numSM, String descriptionAM, float tarrifAM) {
        this.idRv = idRv;
        this.numSM = numSM;
        this.descriptionAM = descriptionAM;
        this.tarrifAM = tarrifAM;
    }

    public int getIdRv() {
        return idRv;
    }

    public void setIdRv(int idRv) {
        this.idRv = idRv;
    }

    public int getNumSM() {
        return numSM;
    }

    public void setNumSM(int numSM) {
        this.numSM = numSM;
    }

    public String getDescriptionAM() {
        return descriptionAM;
    }

    public void setDescriptionAM(String descriptionAM) {
        this.descriptionAM = descriptionAM;
    }

    public float getTarrifAM() {
        return tarrifAM;
    }

    public void setTarrifAM(float tarrifAM) {
        this.tarrifAM = tarrifAM;
    }
}
