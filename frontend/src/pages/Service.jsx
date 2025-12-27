import React, { useState, useEffect } from 'react';
import { serviceMedicalApi } from '../services/api';
import './Service.css';

const Service = () => {
  const [services, setServices] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    nom: '',
    type: '',
    description: '',
    tarif: '',
    nombreSeances: '',
    affiche: null
  });

  useEffect(() => {
    loadServices();
  }, []);

  const loadServices = async () => {
    try {
      const data = await serviceMedicalApi.getAll();
      setServices(data);
    } catch (error) {
      console.error('Error loading services:', error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const serviceData = {
        nom: formData.nom,
        type: formData.type,
        description: formData.description,
        tarif: parseFloat(formData.tarif),
        nombreSeances: parseInt(formData.nombreSeances)
      };
      await serviceMedicalApi.create(serviceData);
      handleCancel();
      loadServices(); // Reload services after creation
      alert('Service créé avec succès!');
    } catch (error) {
      console.error('Error creating service:', error);
      alert('Erreur lors de la création du service');
    }
  };

  const handleCancel = () => {
    setFormData({
      nom: '',
      type: '',
      description: '',
      tarif: '',
      nombreSeances: '',
      affiche: null
    });
    setShowForm(false);
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setFormData({ ...formData, affiche: file });
    }
  };

  const getServiceIcon = (type) => {
    const icons = {
      'CONSULTATION': '🦷',
      'SOIN_DENTAIRE': '🔍',
      'CHIRURGIE': '⚕️',
      'ORTHODONTIE': '🦴',
      'IMPLANTOLOGIE': '🦷',
      'PROTHESE': '🦷',
      'ESTHETIQUE': '✨',
      'PREVENTION': '🛡️',
      'URGENCE': '🚨'
    };
    return icons[type] || '🏥';
  };

  const getServiceBadgeColor = (type) => {
    const colors = {
      'CONSULTATION': '#6c5ce7',
      'SOIN_DENTAIRE': '#0984e3',
      'CHIRURGIE': '#d63031',
      'ORTHODONTIE': '#fd79a8',
      'IMPLANTOLOGIE': '#00b894',
      'PROTHESE': '#fdcb6e',
      'ESTHETIQUE': '#a29bfe',
      'PREVENTION': '#55efc4',
      'URGENCE': '#ff7675'
    };
    return colors[type] || '#6c5ce7';
  };

  return (
    <div className="services-page">
      <div className="services-header">
        <h1>Nos Services Dentaires</h1>
        <p className="subtitle">Des soins de qualité pour votre sourire</p>
      </div>

      <div className="admin-section">
        <button 
          className="btn btn-primary"
          onClick={() => setShowForm(!showForm)}
        >
          {showForm ? 'Fermer' : '+ Nouveau Service'}
        </button>
      </div>

      {showForm && (
        <div className="form-card">
          <h3>Créer un nouveau service dentaire</h3>
          <form onSubmit={handleSubmit}>
            <div className="form-row">
              <div className="form-group">
                <label>Nom du service: *</label>
                <input
                  type="text"
                  value={formData.nom}
                  onChange={(e) => setFormData({...formData, nom: e.target.value})}
                  className="form-control"
                  placeholder="Ex: Détartrage"
                  required
                />
              </div>
              
              <div className="form-group">
                <label>Prix (DT): *</label>
                <input
                  type="number"
                  step="0.01"
                  min="0"
                  value={formData.tarif}
                  onChange={(e) => setFormData({...formData, tarif: e.target.value})}
                  className="form-control"
                  placeholder="Ex: 50.00"
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Nombre de séances: *</label>
                <input
                  type="number"
                  min="1"
                  value={formData.nombreSeances}
                  onChange={(e) => setFormData({...formData, nombreSeances: e.target.value})}
                  className="form-control"
                  placeholder="Ex: 3"
                  required
                />
              </div>
              
              <div className="form-group">
                <label>Type / Catégorie: *</label>
                <select
                  value={formData.type}
                  onChange={(e) => setFormData({...formData, type: e.target.value})}
                  className="form-control"
                  required
                >
                  <option value="">-- Sélectionner un type --</option>
                  <option value="CONSULTATION">Consultation</option>
                  <option value="SOIN_DENTAIRE">Soin Dentaire</option>
                  <option value="CHIRURGIE">Chirurgie</option>
                  <option value="ORTHODONTIE">Orthodontie</option>
                  <option value="IMPLANTOLOGIE">Implantologie</option>
                  <option value="PROTHESE">Prothèse</option>
                  <option value="ESTHETIQUE">Esthétique</option>
                  <option value="PREVENTION">Prévention</option>
                  <option value="URGENCE">Urgence</option>
                </select>
              </div>
            </div>

            <div className="form-group">
              <label>Affiche (Image):</label>
              <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
                className="form-control"
              />
              {formData.affiche && (
                <small className="form-text text-muted">
                  Fichier sélectionné: {formData.affiche.name}
                </small>
              )}
            </div>

            <div className="form-group">
              <label>Description:</label>
              <textarea
                value={formData.description}
                onChange={(e) => setFormData({...formData, description: e.target.value})}
                className="form-control"
                rows="4"
                placeholder="Description détaillée du service..."
              />
            </div>

            <div className="form-actions" style={{ display: 'flex', gap: '10px', marginTop: '20px' }}>
              <button type="submit" className="btn btn-success">
                Enregistrer
              </button>
              <button type="button" className="btn btn-secondary" onClick={handleCancel}>
                Annuler
              </button>
            </div>
          </form>
        </div>
      )}

      <div className="services-grid">
        {services.length === 0 ? (
          <div className="no-services">
            <p>Aucun service disponible pour le moment.</p>
            <p>Cliquez sur "Nouveau Service" pour en créer un.</p>
          </div>
        ) : (
          services.map(service => (
            <div key={service.id} className="service-card">
              <div className="service-icon">
                <span className="icon-emoji">{getServiceIcon(service.type)}</span>
              </div>
              <h3 className="service-title">{service.nom}</h3>
              <span 
                className="service-badge" 
                style={{ backgroundColor: getServiceBadgeColor(service.type) }}
              >
                {service.type?.replace(/_/g, ' ')}
              </span>
              <p className="service-description">
                {service.description || 'Description du service dentaire'}
              </p>
              <div className="service-footer">
                <span className="service-price">
                  Tarif: <strong>{service.tarif} DT</strong>
                </span>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Service;
