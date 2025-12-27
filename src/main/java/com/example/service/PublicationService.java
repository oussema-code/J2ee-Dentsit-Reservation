package com.example.service;

import com.example.entitys.Publication;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PublicationService {
    
    @PersistenceContext(unitName = "j2eePU")
    private EntityManager em;
    
    public Publication create(Publication publication) {
        em.persist(publication);
        return publication;
    }
    
    public Publication findById(int id) {
        return em.find(Publication.class, id);
    }
    
    public List<Publication> findAll() {
        return em.createQuery("SELECT p FROM Publication p", Publication.class).getResultList();
    }
    
    public Publication update(Publication publication) {
        return em.merge(publication);
    }
    
    public void delete(int id) {
        Publication publication = findById(id);
        if (publication != null) {
            em.remove(publication);
        }
    }
}
