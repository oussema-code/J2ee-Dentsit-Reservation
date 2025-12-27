import React from 'react';

const Publication = () => {
  return (
    <div className="page-container">
      <h2>Publications</h2>
      
      <div className="info-card">
        <h3>À propos du cabinet</h3>
        <p>Bienvenue dans notre cabinet dentaire moderne et équipé des dernières technologies.</p>
        <p>Notre équipe de professionnels qualifiés est à votre service pour tous vos besoins dentaires.</p>
      </div>

      <div className="info-card">
        <h3>Nos Services</h3>
        <ul style={{ lineHeight: '2rem', marginLeft: '2rem' }}>
          <li>Consultations générales</li>
          <li>Soins dentaires</li>
          <li>Orthodontie</li>
          <li>Implants dentaires</li>
          <li>Blanchiment dentaire</li>
          <li>Urgences dentaires</li>
        </ul>
      </div>

      <div className="info-card">
        <h3>Horaires d'ouverture</h3>
        <p><strong>Lundi - Vendredi:</strong> 8h00 - 18h00</p>
        <p><strong>Samedi:</strong> 9h00 - 13h00</p>
        <p><strong>Dimanche:</strong> Fermé</p>
      </div>

      <div className="info-card">
        <h3>Contact</h3>
        <p><strong>Adresse:</strong> Tunis, Tunisie</p>
        <p><strong>Téléphone:</strong> +216 XX XXX XXX</p>
        <p><strong>Email:</strong> contact@cabinetdentaire.com</p>
      </div>
    </div>
  );
};

export default Publication;
