package com.example.entitys;
import jakarta.persistence.*;
@Entity
@Table(name = "acte_medical")
public class ActeMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAM;
    @Column(nullable= false)
    private int idRv;
    @Column(nullable= false)
    private int numSM;
    private String descriptionAM;
    @Column(length = 6, precision = 2)
    private float tarrifAM;
    //relations
    @ManyToOne
    @JoinColumn(name = "idRv")
    private RendezVous rendezVous;
    @ManyToOne
    @JoinColumn(name = "numSM")
    private ServiceMedical serviceMedical;
    // getters & setters
    public int getIdAM() {
        return idAM;
    }
    public void setIdAM(int idAM) {
        this.idAM = idAM;
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
