package com.example.service;
import jakarta.ejb.Stateless;
import jakarta.persistence.PersistenceContext;
@Stateless
public class ServiceMedicalService {
    @PersistenceContext
    private EntityManager em;
    public ServiceMedical create(ServiceMedical sm){
        em.persist(sm);
        return sm;
    }
    public ServiceMedical findById(int id){
        return em.find(ServiceMedical.class,id);
    }
    public List<ServiceMedical> findAll(){
        return em.createQuery(
            "SELECT s FROM ServiceMedical s",ServiceMedical.class
        ).getResultList();
    }
    public ServiceMedical update(ServiceMedical sm){
        return em.merge(sm);
    }
    public void delete(int id){
        ServiceMedical sm=findById(id);
        if(sm!=null){
            em.remove(sm);
        }
        
    
}
