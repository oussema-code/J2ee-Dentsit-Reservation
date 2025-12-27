package com.example.service;

import com.example.entitys.AideSoignant;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AideSoignantService {
    
    @PersistenceContext(unitName = "j2eePU")
    private EntityManager em;
    
    public AideSoignant create(AideSoignant aideSoignant) {
        em.persist(aideSoignant);
        return aideSoignant;
    }
    
    public AideSoignant findById(int id) {
        return em.find(AideSoignant.class, id);
    }
    
    public List<AideSoignant> findAll() {
        return em.createQuery("SELECT a FROM AideSoignant a", AideSoignant.class).getResultList();
    }
    
    public AideSoignant update(AideSoignant aideSoignant) {
        return em.merge(aideSoignant);
    }
    
    public void delete(int id) {
        AideSoignant aideSoignant = findById(id);
        if (aideSoignant != null) {
            em.remove(aideSoignant);
        }
    }
}
