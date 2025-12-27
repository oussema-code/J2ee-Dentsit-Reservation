#!/bin/bash

# Script pour insérer automatiquement les services dans la base de données
# Database configuration from WildFly datasource

DB_NAME="j2eeDentist"
DB_USER="oussema"
DB_PASS="#Bringa03"
DB_HOST="localhost"
DB_PORT="3306"

SQL_FILE="src/main/resources/seed-services.sql"

echo "=========================================="
echo "Insertion des services dentaires"
echo "=========================================="
echo "Base de données: $DB_NAME"
echo "Utilisateur: $DB_USER"
echo "Hôte: $DB_HOST:$DB_PORT"
echo "=========================================="

# Vérifier si le fichier SQL existe
if [ ! -f "$SQL_FILE" ]; then
    echo "❌ Erreur: Le fichier $SQL_FILE n'existe pas!"
    exit 1
fi

# Exécuter le script SQL
if [ -z "$DB_PASS" ]; then
    # Sans mot de passe
    mysql -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" "$DB_NAME" < "$SQL_FILE"
else
    # Avec mot de passe
    mysql -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "$SQL_FILE"
fi

# Vérifier le résultat
if [ $? -eq 0 ]; then
    echo "✅ Services insérés avec succès!"
    echo ""
    echo "Pour vérifier, exécutez:"
    echo "  mysql -u $DB_USER -p $DB_NAME -e 'SELECT nomSM, typeSM, tarifSM FROM ServiceMedical;'"
else
    echo "❌ Erreur lors de l'insertion des services"
    exit 1
fi
