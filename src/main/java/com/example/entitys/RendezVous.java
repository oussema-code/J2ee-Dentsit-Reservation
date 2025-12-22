package com.example.entitys;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "rendez_vous")

public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment id
    private int idRv;
    @Column(nullable = false,length = 8)
    private int idP;
    @Column(nullable = false,length = 8)
    private int idD;
    @Column(nullable = false)
    private Date dateRv;
    @Column(nullable = false)
    private Time heureRv;
    @Column(nullable = false,length = 100)
    private String statutRv;
    private String descriptionRv;
    //relations
    @ManyToOne
    @JoinColumn(name = "idP")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name="idD")
    private Dentiste dentiste;
    @OneToMany(mappedBy = "rendezVous")
    private List<ActeMedical> actMedicals;
    // getters & setters
    public int getIdRv() {
        return idRv;
    }
    public void setIdRv(int idRv) {
        this.idRv = idRv;
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
    public Date getDateRv() {
        return dateRv;
    }
    public void setDateRv(Date dateRv) {
        this.dateRv = dateRv;
    }
    public Time getHeureRv() {
        return heureRv;
    }
    public void setHeureRv(Time heureRv) {
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
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Dentiste getDentiste() {
        return dentiste;
    }
    public void setDentiste(Dentiste dentiste) {
        this.dentiste = dentiste;
    }
    public List<ActeMedical> getActMedicals() {
        return actMedicals;
    }
    public void setActMedicals(List<ActeMedical> actMedicals) {
        this.actMedicals = actMedicals;
    }
    


    

    




}
