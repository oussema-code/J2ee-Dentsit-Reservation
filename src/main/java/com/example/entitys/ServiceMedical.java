package com.example.entitys;
import jakarta.persistence.*;
import com.example.entitys.ActeMedical;
import java.util.List;
@Entity
@Table(name = "service_medical")

public class ServiceMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int numSM;
    @Column(nullable = false, length = 100)
    private String nomSM;
    @Column(nullable=false,length = 100)
    private String typeSM;
    private String descriptionSM;
    @Column(length = 6, precision = 2)
    
    private float tarifSM;
    //relations
    @OneToMany(mappedBy = "serviceMedical")
    private List<ActeMedical> acteMedicals;
    
    // getters & setters
    public int getNumSM() {
        return numSM;
    }
    public void setNumSM(int numSM) {
        this.numSM = numSM;
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
    public List<ActeMedical> getActeMedicals() {
        return acteMedicals;
    }
    public void setActeMedicals(List<ActeMedical> acteMedicals) {
        this.acteMedicals = acteMedicals;
    }
    
    

}
