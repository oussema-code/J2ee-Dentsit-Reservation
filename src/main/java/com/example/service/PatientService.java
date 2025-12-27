package com.example.service;
import java.util.List;
import com.example.entitys.Patient;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PatientService {
    @PersistenceContext
    private EntityManager em;
    
    //Create 
    public Patient create(Patient patient){
        em.persist(patient);
        return patient;
    }
    //Read by ID
    public Patient findById(int id){
        return em.find(Patient.class,id);
    }
    //Read all
    public List<Patient> findAll(){
        return em.createQuery(
            "SELECT p FROM Patient p",Patient.class
        ).getResultList();
    }
    //Update
    public Patient update(Patient patient){
        return em.merge(patient);
    }
    //Delete
    public void delete(int id){
        Patient p=findById(id);
        if(p!=null){
            em.remove(p);
        }

    }
}
