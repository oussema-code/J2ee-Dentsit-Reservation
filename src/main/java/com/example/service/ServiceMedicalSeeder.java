package com.example.service;

import com.example.entitys.ServiceMedical;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to seed the database with dental services
 * Can be called via a REST endpoint or command line
 */
@Stateless
public class ServiceMedicalSeeder {

    @PersistenceContext(unitName = "j2eePU")
    private EntityManager em;

    @Transactional
    public int seedServices() {
        List<ServiceMedical> services = createServicesList();
        
        int count = 0;
        for (ServiceMedical service : services) {
            em.persist(service);
            count++;
        }
        
        return count;
    }

    private List<ServiceMedical> createServicesList() {
        List<ServiceMedical> services = new ArrayList<>();

        // Dentisterie générale (Soins courants et de base)
        services.add(createService("Consultation dentaire", "CONSULTATION", "Examen complet de la santé bucco-dentaire", 50.00, 1));
        services.add(createService("Détartrage", "SOIN_DENTAIRE", "Nettoyage professionnel des dents et élimination du tartre", 80.00, 1));
        services.add(createService("Polissage dentaire", "SOIN_DENTAIRE", "Polissage des dents pour un sourire éclatant", 40.00, 1));
        services.add(createService("Traitement de carie simple", "SOIN_DENTAIRE", "Traitement et obturation d'une carie dentaire", 100.00, 1));
        services.add(createService("Obturation (plombage)", "SOIN_DENTAIRE", "Restauration dentaire avec matériau composite", 90.00, 1));
        services.add(createService("Bilan bucco-dentaire", "CONSULTATION", "Évaluation complète de la santé dentaire et gingivale", 60.00, 1));
        services.add(createService("Conseils d'hygiène bucco-dentaire", "PREVENTION", "Conseils personnalisés pour maintenir une bonne hygiène", 30.00, 1));

        // Diagnostic et soins courants
        services.add(createService("Examen clinique", "CONSULTATION", "Examen détaillé de la cavité buccale", 45.00, 1));
        services.add(createService("Diagnostic dentaire", "CONSULTATION", "Établissement d'un diagnostic précis des pathologies dentaires", 55.00, 1));
        services.add(createService("Suivi post-traitement", "CONSULTATION", "Contrôle et suivi après intervention", 40.00, 1));
        services.add(createService("Évaluation des douleurs dentaires", "CONSULTATION", "Diagnostic des causes de douleurs dentaires", 50.00, 1));
        services.add(createService("Contrôle dentaire périodique", "PREVENTION", "Examen de routine pour prévenir les problèmes dentaires", 40.00, 1));

        // Parodontologie (Soins des gencives et du support dentaire)
        services.add(createService("Traitement de la gingivite", "SOIN_DENTAIRE", "Traitement de l'inflammation des gencives", 120.00, 2));
        services.add(createService("Traitement de la parodontite", "SOIN_DENTAIRE", "Traitement des maladies parodontales avancées", 250.00, 3));
        services.add(createService("Surfaçage radiculaire", "SOIN_DENTAIRE", "Nettoyage en profondeur des racines dentaires", 180.00, 2));
        services.add(createService("Détartrage profond", "SOIN_DENTAIRE", "Détartrage sous-gingival et nettoyage profond", 150.00, 2));
        services.add(createService("Traitement des poches parodontales", "SOIN_DENTAIRE", "Traitement des espaces entre la gencive et la dent", 200.00, 3));

        // Radiologie et imagerie dentaire
        services.add(createService("Radiographie rétro-alvéolaire", "CONSULTATION", "Radiographie détaillée d'une ou plusieurs dents", 35.00, 1));
        services.add(createService("Radiographie panoramique", "CONSULTATION", "Vue d'ensemble complète de la dentition", 80.00, 1));
        services.add(createService("Radiographie péri-apicale", "CONSULTATION", "Radiographie de la zone autour de la racine dentaire", 40.00, 1));
        services.add(createService("Scanner dentaire (CBCT)", "CONSULTATION", "Imagerie 3D haute résolution des structures dentaires", 200.00, 1));
        services.add(createService("Bilan radiologique dentaire", "CONSULTATION", "Ensemble d'examens radiologiques complets", 150.00, 1));

        // Actes chirurgicaux
        services.add(createService("Extraction dentaire simple", "CHIRURGIE", "Extraction d'une dent non compliquée", 80.00, 1));
        services.add(createService("Extraction de dent de sagesse", "CHIRURGIE", "Extraction chirurgicale de dent de sagesse", 200.00, 1));
        services.add(createService("Chirurgie gingivale", "CHIRURGIE", "Intervention chirurgicale sur les gencives", 350.00, 1));
        services.add(createService("Résection apicale", "CHIRURGIE", "Ablation chirurgicale de l'apex radiculaire", 400.00, 1));
        services.add(createService("Incision et drainage d'abcès", "URGENCE", "Traitement chirurgical d'un abcès dentaire", 150.00, 1));

        // Endodontie (Soins de la pulpe dentaire)
        services.add(createService("Dévitalisation", "SOIN_DENTAIRE", "Traitement de canal et retrait du nerf dentaire", 250.00, 2));
        services.add(createService("Traitement de canal", "SOIN_DENTAIRE", "Nettoyage et obturation des canaux radiculaires", 280.00, 3));
        services.add(createService("Retrait d'ancien traitement endodontique", "SOIN_DENTAIRE", "Reprise d'un traitement de canal existant", 320.00, 2));
        services.add(createService("Obturation canalaire", "SOIN_DENTAIRE", "Remplissage définitif des canaux radiculaires", 200.00, 1));
        services.add(createService("Traitement des infections pulpaires", "SOIN_DENTAIRE", "Traitement des infections de la pulpe dentaire", 230.00, 2));

        // Esthétique dentaire
        services.add(createService("Blanchiment des dents", "ESTHETIQUE", "Éclaircissement professionnel des dents", 300.00, 1));
        services.add(createService("Facettes dentaires", "ESTHETIQUE", "Pose de facettes en céramique pour un sourire parfait", 800.00, 2));
        services.add(createService("Polissage esthétique", "ESTHETIQUE", "Polissage spécialisé pour améliorer l'apparence", 60.00, 1));
        services.add(createService("Éclaircissement dentaire", "ESTHETIQUE", "Traitement de blanchiment dentaire avancé", 350.00, 2));
        services.add(createService("Restauration esthétique", "ESTHETIQUE", "Restauration complète de l'esthétique dentaire", 500.00, 3));

        // Implantologie
        services.add(createService("Pose d'implant dentaire", "IMPLANTOLOGIE", "Mise en place chirurgicale d'un implant dentaire", 1200.00, 1));
        services.add(createService("Consultation implantologique", "IMPLANTOLOGIE", "Évaluation et planification d'implants dentaires", 100.00, 1));
        services.add(createService("Mise en place de pilier implantaire", "IMPLANTOLOGIE", "Installation du pilier sur l'implant", 300.00, 1));
        services.add(createService("Suivi implantaire", "IMPLANTOLOGIE", "Contrôle et maintenance des implants", 80.00, 1));
        services.add(createService("Greffe osseuse pré-implantaire", "IMPLANTOLOGIE", "Augmentation osseuse avant pose d'implant", 800.00, 1));

        return services;
    }

    private ServiceMedical createService(String nom, String type, String description, double tarif, int nombreSeances) {
        ServiceMedical service = new ServiceMedical();
        service.setNomSM(nom);
        service.setTypeSM(type);
        service.setDescriptionSM(description);
        service.setTarifSM((float) tarif); // Cast to float
        // Note: Add nombreSeances field to ServiceMedical entity if not exists
        return service;
    }
}
