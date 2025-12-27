package com.example.service;
import java.util.List;
import com.example.entitys.RendezVous;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RendezVousService {
    @PersistenceContext
    private EntityManager em;
    
    public RendezVous create(RendezVous rv){
        if(rv.getPatient()==null || rv.getDentiste()==null){
            throw new IllegalArgumentException("Patient and Dentiste must not be null");
        }
        em.persist(rv);
        return rv;
    }
    
    public RendezVous findById(int id){
        return em.find(RendezVous.class, id);
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

