import React, { useState, useEffect } from 'react';
import { publicationApi } from '../services/api';
import { useAuth } from '../context/AuthContext';
import '../pages/Service.css';

const Publication = () => {
  const { user, userType } = useAuth();
  const [publications, setPublications] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    titrePub: '',
    datePublicationPub: '',
    affichePub: '',
    typePublicationPub: '',
    resumePub: ''
  });

  useEffect(() => {
    loadPublications();
  }, []);

  const loadPublications = async () => {
    try {
      const data = await publicationApi.getAll();
      setPublications(data);
    } catch (error) {
      console.error('Error loading publications:', error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!user || userType !== 'aidesoignant') {
      alert('Vous devez être connecté en tant qu\'aide-soignant pour créer une publication');
      return;
    }

    try {
      const publicationData = {
        titrePub: formData.titrePub,
        datePublicationPub: formData.datePublicationPub,
        affichePub: formData.affichePub,
        typePublicationPub: formData.typePublicationPub,
        resumePub: formData.resumePub,
        idAS: user.idAS
      };

      await publicationApi.create(publicationData);
      handleCancel();
      loadPublications();
      alert('Publication créée avec succès!');
    } catch (error) {
      console.error('Error creating publication:', error);
      alert('Erreur lors de la création de la publication');
    }
  };

  const handleCancel = () => {
    setFormData({
      titrePub: '',
      datePublicationPub: '',
      affichePub: '',
      typePublicationPub: '',
      resumePub: ''
    });
    setShowForm(false);
  };

  const handleFileChange = (e, field) => {
    const file = e.target.files[0];
    if (file) {
      // For now, just store the filename
      // In production, you'd upload to server and get URL
      setFormData({...formData, [field]: file.name});
    }
  };

  return (
    <div className="services-page">
      <div className="services-header" style={{ 
        background: 'linear-gradient(135deg, #FF7E5F 0%, #FF6B99 100%)',
        padding: '40px 20px',
        borderRadius: '15px',
        marginBottom: '30px',
        color: 'white',
        textAlign: 'center'
      }}>
        <h1>Publications Dentaires</h1>
        <p className="subtitle">Découvrez les dernières avancées en dentisterie pour des soins plus précis, confortables et esthétiques</p>
      </div>

      {userType === 'aidesoignant' && (
        <div className="admin-section">
          <button 
            className="btn btn-primary"
            onClick={() => setShowForm(!showForm)}
          >
            {showForm ? 'Fermer' : '+ Nouvelle Publication'}
          </button>
        </div>
      )}

      {showForm && userType === 'aidesoignant' && (
        <div className="form-card" style={{ 
          background: 'white',
          padding: '30px',
          borderRadius: '10px',
          boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
          marginBottom: '30px'
        }}>
          <h3 style={{ color: '#333', marginBottom: '20px' }}>Créer une nouvelle publication</h3>
          <form onSubmit={handleSubmit}>
            <div className="form-row">
              <div className="form-group" style={{ flex: 2 }}>
                <label>Titre: *</label>
                <input
                  type="text"
                  value={formData.titrePub}
                  onChange={(e) => setFormData({...formData, titrePub: e.target.value})}
                  className="form-control"
                  placeholder="Saisir le titre de publication"
                  required
                />
              </div>
              
              <div className="form-group" style={{ flex: 1 }}>
                <label>Date de publication: *</label>
                <input
                  type="date"
                  value={formData.datePublicationPub}
                  onChange={(e) => setFormData({...formData, datePublicationPub: e.target.value})}
                  className="form-control"
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Fichier:</label>
                <input
                  type="file"
                  onChange={(e) => handleFileChange(e, 'fichier')}
                  className="form-control"
                  accept=".pdf,.doc,.docx"
                />
                <small className="form-text text-muted">Formats acceptés: PDF, DOC, DOCX</small>
              </div>
              
              <div className="form-group">
                <label>Affiche:</label>
                <input
                  type="file"
                  onChange={(e) => handleFileChange(e, 'affichePub')}
                  className="form-control"
                  accept="image/*"
                />
                <small className="form-text text-muted">Formats acceptés: JPG, PNG, GIF</small>
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Type de publication: *</label>
                <select
                  value={formData.typePublicationPub}
                  onChange={(e) => setFormData({...formData, typePublicationPub: e.target.value})}
                  className="form-control"
                  required
                >
                  <option value="">[Choisir catégorie]</option>
                  <option value="ACTUALITE">Actualité</option>
                  <option value="CONSEIL">Conseil</option>
                  <option value="RECHERCHE">Recherche</option>
                  <option value="TECHNOLOGIE">Technologie</option>
                  <option value="PREVENTION">Prévention</option>
                  <option value="EVENEMENT">Événement</option>
                </select>
              </div>
            </div>

            <div className="form-group">
              <label>Résumé: *</label>
              <textarea
                value={formData.resumePub}
                onChange={(e) => setFormData({...formData, resumePub: e.target.value})}
                className="form-control"
                rows="5"
                placeholder="Saisir le description associée..."
                required
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
        {publications.length === 0 ? (
          <div className="no-services">
            <p>Aucune publication disponible pour le moment.</p>
            {userType === 'aidesoignant' && (
              <p>Cliquez sur "Nouvelle Publication" pour en créer une.</p>
            )}
          </div>
        ) : (
          publications.map(pub => (
            <div key={pub.idPub} className="service-card">
              {pub.affichePub && (
                <div style={{ 
                  width: '100%', 
                  height: '200px', 
                  background: `url(${pub.affichePub}) center/cover`,
                  borderRadius: '8px 8px 0 0',
                  marginBottom: '15px'
                }} />
              )}
              <h3 className="service-title">{pub.titrePub}</h3>
              <span 
                className="service-badge" 
                style={{ backgroundColor: '#FF7E5F' }}
              >
                {pub.typePublicationPub}
              </span>
              <p className="service-description">
                {pub.resumePub}
              </p>
              <div className="service-footer">
                <span style={{ fontSize: '0.9em', color: '#666' }}>
                  📅 {pub.datePublicationPub}
                </span>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Publication;
