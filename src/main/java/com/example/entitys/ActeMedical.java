package com.example.entitys;
import jakarta.persistence.*;
@Entity
@Table(name = "acte_medical")
public class ActeMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAM;
    
    private String descriptionAM;
    
    @Column(length = 6, precision = 2)
    private float tarrifAM;
    
    //relations
    @ManyToOne
    @JoinColumn(name = "idRv", nullable = false)
    private RendezVous rendezVous;
    
    @ManyToOne
    @JoinColumn(name = "numSM", nullable = false)
    private ServiceMedical serviceMedical;
    // getters & setters
    public int getIdAM() {
        return idAM;
    }
    public void setIdAM(int idAM) {
        this.idAM = idAM;
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
    public RendezVous getRendezVous() {
        return rendezVous;
    }
    public void setRendezVous(RendezVous rendezVous) {
        this.rendezVous = rendezVous;
    }
    public ServiceMedical getServiceMedical() {
        return serviceMedical;
    }
    public void setServiceMedical(ServiceMedical serviceMedical) {
        this.serviceMedical = serviceMedical;
    }
    


}
