package com.example.service;
import jakarta.ejb.Stateless;
@Stateless
public class DentisteService {
    @PersistenceContext
    private EntityManager entityManager;
    public Dentiste create(Dentiste dentiste) {
        entityManager.persist(dentiste);
        return dentiste;
    }
    public Dentiste findById(int id) {
        return entityManager.find(Dentiste.class, id);
    }
    public List<Dentiste> findAll() {
        return entityManager.createQuery("SELECT d FROM Dentiste d", Dentiste.class).get
            .getResultList();
    }
    public Dentiste update(Dentiste dentiste) {
        return entityManager.merge(dentiste);
    }
    public void delete(int id) {
        Dentiste d = findById(id);
        if (d != null) {
            entityManager.remove(d);
        }
        
    
}
