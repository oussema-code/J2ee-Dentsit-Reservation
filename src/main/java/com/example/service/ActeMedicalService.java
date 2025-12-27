package com.example.service;
import java.util.List;
import com.example.entitys.ActeMedical;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ActeMedicalService {
    @PersistenceContext
    private EntityManager em;
    
    public ActeMedical create(ActeMedical acteMedical){
        if(acteMedical.getRendezVous()==null || acteMedical.getServiceMedical()==null){
            throw new IllegalArgumentException("RendezVous and ServiceMedical must not be null");
        }
        em.persist(acteMedical);
        return acteMedical;
    }
    
    public List<ActeMedical> findByRendezVousId(int rvId){
        return em.createQuery(
            "SELECT a FROM ActeMedical a WHERE a.rendezVous.idRv=:id",ActeMedical.class
        ).setParameter("id",rvId).getResultList();
    }
    
    public List<ActeMedical> findByServiceMedicalId(int smId){
        return em.createQuery(
            "SELECT a FROM ActeMedical a WHERE a.serviceMedical.numSM=:id",ActeMedical.class
        ).setParameter("id",smId).getResultList();
    }
}

