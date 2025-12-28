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
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        padding: '3rem 2rem',
        borderRadius: '1rem',
        marginBottom: '2rem',
        color: 'white',
        textAlign: 'center',
        boxShadow: '0 10px 25px rgba(102, 126, 234, 0.3)'
      }}>
        <h1 style={{ fontSize: '2.5rem', fontWeight: '700', marginBottom: '0.75rem' }}>Publications Dentaires</h1>
        <p className="subtitle" style={{ fontSize: '1.1rem', opacity: 0.95 }}>Découvrez les dernières avancées en dentisterie pour des soins plus précis, confortables et esthétiques</p>
      </div>

      {userType === 'aidesoignant' && (
        <div className="admin-section" style={{ marginBottom: '2rem' }}>
          <button 
            className="btn btn-primary"
            onClick={() => setShowForm(!showForm)}
            style={{
              padding: '0.875rem 2rem',
              fontSize: '1.05rem',
              fontWeight: '600',
              borderRadius: '0.75rem',
              boxShadow: '0 4px 6px rgba(102, 126, 234, 0.2)',
              transition: 'all 0.3s ease'
            }}
          >
            {showForm ? '✕ Fermer' : '+ Nouvelle Publication'}
          </button>
        </div>
      )}

      {showForm && userType === 'aidesoignant' && (
        <div className="form-card" style={{ 
          background: 'white',
          padding: '2.5rem',
          borderRadius: '1rem',
          boxShadow: '0 4px 6px rgba(0,0,0,0.07)',
          marginBottom: '2rem',
          border: '1px solid #e2e8f0'
        }}>
          <h3 style={{ color: '#1e293b', marginBottom: '1.5rem', fontSize: '1.5rem', fontWeight: '700' }}>Créer une nouvelle publication</h3>
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
            <div key={pub.idPub} className="service-card" style={{
              background: 'white',
              borderRadius: '1rem',
              overflow: 'hidden',
              boxShadow: '0 4px 6px rgba(0,0,0,0.07)',
              transition: 'all 0.3s ease',
              border: '1px solid #e2e8f0'
            }}>
              {pub.affichePub && (
                <div style={{ 
                  width: '100%', 
                  height: '200px', 
                  background: `linear-gradient(135deg, rgba(102, 126, 234, 0.1), rgba(118, 75, 162, 0.1)), url(${pub.affichePub}) center/cover`,
                  marginBottom: '0'
                }} />
              )}
              <div style={{ padding: '1.5rem' }}>
                <h3 className="service-title" style={{ 
                  color: '#1e293b', 
                  fontSize: '1.35rem', 
                  fontWeight: '700',
                  marginBottom: '0.75rem'
                }}>{pub.titrePub}</h3>
                <span 
                  className="service-badge" 
                  style={{ 
                    backgroundColor: '#667eea',
                    color: 'white',
                    padding: '0.375rem 0.875rem',
                    borderRadius: '9999px',
                    fontSize: '0.875rem',
                    fontWeight: '600',
                    display: 'inline-block',
                    marginBottom: '1rem'
                  }}
                >
                  {pub.typePublicationPub}
                </span>
                <p className="service-description" style={{
                  color: '#64748b',
                  lineHeight: '1.6',
                  marginBottom: '1rem'
                }}>
                  {pub.resumePub}
                </p>
                <div className="service-footer" style={{ 
                  paddingTop: '1rem',
                  borderTop: '1px solid #e2e8f0'
                }}>
                  <span style={{ fontSize: '0.875rem', color: '#94a3b8', fontWeight: '500' }}>
                    📅 {pub.datePublicationPub}
                  </span>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Publication;
