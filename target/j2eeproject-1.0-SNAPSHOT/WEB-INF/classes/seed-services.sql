-- Script d'insertion automatique des services dentaires
-- Exécuter ce script dans votre base de données MySQL/PostgreSQL

-- Dentisterie générale (Soins courants et de base)
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Consultation dentaire', 'CONSULTATION', 'Examen complet de la santé bucco-dentaire', 50.00),
('Détartrage', 'SOIN_DENTAIRE', 'Nettoyage professionnel des dents et élimination du tartre', 80.00),
('Polissage dentaire', 'SOIN_DENTAIRE', 'Polissage des dents pour un sourire éclatant', 40.00),
('Traitement de carie simple', 'SOIN_DENTAIRE', 'Traitement et obturation d''une carie dentaire', 100.00),
('Obturation (plombage)', 'SOIN_DENTAIRE', 'Restauration dentaire avec matériau composite', 90.00),
('Bilan bucco-dentaire', 'CONSULTATION', 'Évaluation complète de la santé dentaire et gingivale', 60.00),
('Conseils d''hygiène bucco-dentaire', 'PREVENTION', 'Conseils personnalisés pour maintenir une bonne hygiène', 30.00);

-- Diagnostic et soins courants
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Examen clinique', 'CONSULTATION', 'Examen détaillé de la cavité buccale', 45.00),
('Diagnostic dentaire', 'CONSULTATION', 'Établissement d''un diagnostic précis des pathologies dentaires', 55.00),
('Suivi post-traitement', 'CONSULTATION', 'Contrôle et suivi après intervention', 40.00),
('Évaluation des douleurs dentaires', 'CONSULTATION', 'Diagnostic des causes de douleurs dentaires', 50.00),
('Contrôle dentaire périodique', 'PREVENTION', 'Examen de routine pour prévenir les problèmes dentaires', 40.00);

-- Parodontologie (Soins des gencives et du support dentaire)
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Traitement de la gingivite', 'SOIN_DENTAIRE', 'Traitement de l''inflammation des gencives', 120.00),
('Traitement de la parodontite', 'SOIN_DENTAIRE', 'Traitement des maladies parodontales avancées', 250.00),
('Surfaçage radiculaire', 'SOIN_DENTAIRE', 'Nettoyage en profondeur des racines dentaires', 180.00),
('Détartrage profond', 'SOIN_DENTAIRE', 'Détartrage sous-gingival et nettoyage profond', 150.00),
('Traitement des poches parodontales', 'SOIN_DENTAIRE', 'Traitement des espaces entre la gencive et la dent', 200.00);

-- Radiologie et imagerie dentaire
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Radiographie rétro-alvéolaire', 'CONSULTATION', 'Radiographie détaillée d''une ou plusieurs dents', 35.00),
('Radiographie panoramique', 'CONSULTATION', 'Vue d''ensemble complète de la dentition', 80.00),
('Radiographie péri-apicale', 'CONSULTATION', 'Radiographie de la zone autour de la racine dentaire', 40.00),
('Scanner dentaire (CBCT)', 'CONSULTATION', 'Imagerie 3D haute résolution des structures dentaires', 200.00),
('Bilan radiologique dentaire', 'CONSULTATION', 'Ensemble d''examens radiologiques complets', 150.00);

-- Actes chirurgicaux
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Extraction dentaire simple', 'CHIRURGIE', 'Extraction d''une dent non compliquée', 80.00),
('Extraction de dent de sagesse', 'CHIRURGIE', 'Extraction chirurgicale de dent de sagesse', 200.00),
('Chirurgie gingivale', 'CHIRURGIE', 'Intervention chirurgicale sur les gencives', 350.00),
('Résection apicale', 'CHIRURGIE', 'Ablation chirurgicale de l''apex radiculaire', 400.00),
('Incision et drainage d''abcès', 'URGENCE', 'Traitement chirurgical d''un abcès dentaire', 150.00);

-- Endodontie (Soins de la pulpe dentaire)
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Dévitalisation', 'SOIN_DENTAIRE', 'Traitement de canal et retrait du nerf dentaire', 250.00),
('Traitement de canal', 'SOIN_DENTAIRE', 'Nettoyage et obturation des canaux radiculaires', 280.00),
('Retrait d''ancien traitement endodontique', 'SOIN_DENTAIRE', 'Reprise d''un traitement de canal existant', 320.00),
('Obturation canalaire', 'SOIN_DENTAIRE', 'Remplissage définitif des canaux radiculaires', 200.00),
('Traitement des infections pulpaires', 'SOIN_DENTAIRE', 'Traitement des infections de la pulpe dentaire', 230.00);

-- Esthétique dentaire
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Blanchiment des dents', 'ESTHETIQUE', 'Éclaircissement professionnel des dents', 300.00),
('Facettes dentaires', 'ESTHETIQUE', 'Pose de facettes en céramique pour un sourire parfait', 800.00),
('Polissage esthétique', 'ESTHETIQUE', 'Polissage spécialisé pour améliorer l''apparence', 60.00),
('Éclaircissement dentaire', 'ESTHETIQUE', 'Traitement de blanchiment dentaire avancé', 350.00),
('Restauration esthétique', 'ESTHETIQUE', 'Restauration complète de l''esthétique dentaire', 500.00);

-- Implantologie
INSERT INTO service_medical (nomSM, typeSM, descriptionSM, tarifSM) VALUES
('Pose d''implant dentaire', 'IMPLANTOLOGIE', 'Mise en place chirurgicale d''un implant dentaire', 1200.00),
('Consultation implantologique', 'IMPLANTOLOGIE', 'Évaluation et planification d''implants dentaires', 100.00),
('Mise en place de pilier implantaire', 'IMPLANTOLOGIE', 'Installation du pilier sur l''implant', 300.00),
('Suivi implantaire', 'IMPLANTOLOGIE', 'Contrôle et maintenance des implants', 80.00),
('Greffe osseuse pré-implantaire', 'IMPLANTOLOGIE', 'Augmentation osseuse avant pose d''implant', 800.00);

-- Pour vérifier l'insertion
SELECT COUNT(*) as 'Nombre de services insérés' FROM service_medical;
