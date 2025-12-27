# Guide d'insertion des services dentaires

## 📋 Services inclus

**Total: 47 services dentaires**

### Catégories:
- ✅ Dentisterie générale (7 services)
- ✅ Diagnostic et soins courants (5 services)
- ✅ Parodontologie (5 services)
- ✅ Radiologie et imagerie (5 services)
- ✅ Actes chirurgicaux (5 services)
- ✅ Endodontie (5 services)
- ✅ Esthétique dentaire (5 services)
- ✅ Implantologie (5 services)

## 🚀 Méthodes d'insertion

### Méthode 1: Script SQL (Recommandé)

```bash
# Exécuter le script shell
./insert-services.sh [database_name] [username] [password]

# Exemple:
./insert-services.sh j2eedb root mypassword

# Ou directement avec MySQL:
mysql -u root -p j2eedb < src/main/resources/seed-services.sql
```

### Méthode 2: REST API Endpoint

```bash
# Démarrer votre serveur WildFly, puis:
curl -X POST http://localhost:8080/j2eeproject-1.0-SNAPSHOT/api/servicemedicals/seed
```

### Méthode 3: Insertion manuelle

Copiez le contenu de `src/main/resources/seed-services.sql` et exécutez-le dans votre client SQL préféré (MySQL Workbench, phpMyAdmin, etc.)

## ✅ Vérification

Après l'insertion, vérifiez que les services sont bien présents:

```sql
SELECT COUNT(*) as total FROM ServiceMedical;
SELECT typeSM, COUNT(*) as count FROM ServiceMedical GROUP BY typeSM;
```

## 📝 Notes

- Les tarifs sont en Dinars Tunisiens (DT)
- Le nombre de séances varie de 1 à 3 selon la complexité
- Les types correspondent aux catégories définies dans votre application:
  - CONSULTATION
  - SOIN_DENTAIRE
  - CHIRURGIE
  - ORTHODONTIE
  - IMPLANTOLOGIE
  - PROTHESE
  - ESTHETIQUE
  - PREVENTION
  - URGENCE
