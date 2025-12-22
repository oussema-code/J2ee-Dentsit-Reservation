package com.example.service;
import javax.swing.text.html.parser.Entity;

import com.example.entitys.RendezVous;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Stateless
public class RendezVousService {
    @PersistenceContext
    private EntityManager em;
    public RendezVous create(RendezVous rv){
        if(rv.getPatient()==null || rv.getDentiste()===null){
            throw new IllegalArgumentException("Patient and Dentiste must not be null");
        }
        em.persist(rv);
        return rv;
    }
    public List<RendezVous> findByPatientId(int patientId){
        return em.createQuery(
            "SELECT r FROM RendezVous r WHERE r.patient.idP=:id",RendezVous.class
        ).setParameter("id",patientId).getResultList();
    }
    public List<RendezVous> findByDentisteId(int dentisteId){
        return em.createQuery(
            "SELECT r FROM RendezVous r WHERE r.dentiste.idD=:id",RendezVous.class
        ).setParameter("id",dentisteId).getResultList();
    }
    
    
    
}
