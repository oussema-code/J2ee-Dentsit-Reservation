import React, { useState } from 'react';
import { patientApi } from '../services/api';

const PatientRegistration = ({ setActivePage, onSwitchToLogin }) => {
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [photoPreview, setPhotoPreview] = useState(null);
  const [formData, setFormData] = useState({
    nom: '',
    prenom: '',
    email: '',
    mdp: '',
    dateNP: '',
    photoP: '',
    groupSanguinP: 'A',
    sexeP: 'M',
    recouvrementP: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      // Create preview URL
      const reader = new FileReader();
      reader.onloadend = () => {
        setPhotoPreview(reader.result);
        setFormData(prev => ({ ...prev, photoP: reader.result }));
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    
    try {
      setLoading(true);
      await patientApi.create(formData);
      setShowConfirmation(true);
      setFormData({
        nom: '',
        prenom: '',
        email: '',
        mdp: '',
        dateNP: '',
        photoP: '',
        groupSanguinP: 'A',
        sexeP: 'M',
        recouvrementP: ''
      });
    } catch (error) {
      console.error('Error creating patient:', error);
      
      // Check if it's a duplicate email error
      const errorMessage = error.message || '';
      if (errorMessage.includes('Duplicate') || errorMessage.includes('UK_9ik4r2rwfxs3p4obl308h0cdi')) {
        setError('Cette adresse email est déjà utilisée. Veuillez vous connecter ou utiliser une autre adresse.');
      } else {
        setError('Erreur lors de l\'enregistrement. Veuillez réessayer.');
      }
    } finally {
      setLoading(false);
    }
  };

  if (showConfirmation) {
    return (
      <div className="confirmation-container">
        <div className="confirmation-card">
          <div className="success-icon">✓</div>
          <h2>Inscription réussie !</h2>
          <p>Votre compte patient a été créé avec succès.</p>
          <p className="encouragement">
            Vous pouvez maintenant vous connecter pour prendre rendez-vous avec nos dentistes.
          </p>
          {onSwitchToLogin ? (
            <button 
              className="btn btn-primary"
              onClick={onSwitchToLogin}
            >
              Se connecter
            </button>
          ) : (
            <button 
              className="btn btn-primary"
              onClick={() => setShowConfirmation(false)}
            >
              Nouvelle inscription
            </button>
          )}
        </div>
      </div>
    );
  }

  return (
    <div className="patient-container">
      <div className="patient-card">
        <h2>Inscription Patient</h2>
        <p className="subtitle">Remplissez le formulaire pour créer votre compte</p>
        
        {error && <div className="alert alert-error">{error}</div>}
        
        <form onSubmit={handleSubmit} className="patient-form">
          <div className="form-row">
            <div className="form-group">
              <label>Nom *</label>
              <input
                type="text"
                name="nom"
                value={formData.nom}
                onChange={handleChange}
                required
                placeholder="Votre nom"
              />
            </div>
            
            <div className="form-group">
              <label>Prénom *</label>
              <input
                type="text"
                name="prenom"
                value={formData.prenom}
                onChange={handleChange}
                required
                placeholder="Votre prénom"
              />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Email *</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                placeholder="votre.email@exemple.com"
              />
            </div>
            
            <div className="form-group">
              <label>Mot de passe *</label>
              <input
                type="password"
                name="mdp"
                value={formData.mdp}
                onChange={handleChange}
                required
                placeholder="••••••••"
              />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Date de naissance *</label>
              <input
                type="date"
                name="dateNP"
                value={formData.dateNP}
                onChange={handleChange}
                required
              />
            </div>
            
            <div className="form-group">
              <label>Sexe *</label>
              <select
                name="sexeP"
                value={formData.sexeP}
                onChange={handleChange}
                required
              >
                <option value="M">Masculin</option>
                <option value="F">Féminin</option>
              </select>
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Groupe sanguin *</label>
              <select
                name="groupSanguinP"
                value={formData.groupSanguinP}
                onChange={handleChange}
                required
              >
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="AB">AB</option>
                <option value="O">O</option>
              </select>
            </div>
            
            <div className="form-group">
              <label>Recouvrement</label>
              <input
                type="text"
                name="recouvrementP"
                value={formData.recouvrementP}
                onChange={handleChange}
                placeholder="Numéro de sécurité sociale"
              />
            </div>
          </div>

          <div className="form-group">
            <label>Photo de profil</label>
            <div className="file-input-wrapper">
              <input
                type="file"
                id="photoP"
                accept="image/*"
                onChange={handleFileChange}
                className="file-input"
              />
              <label htmlFor="photoP" className="file-input-label">
                <span className="file-icon">📷</span>
                {photoPreview ? 'Changer la photo' : 'Sélectionner une photo'}
              </label>
              {photoPreview && (
                <div className="image-preview">
                  <img src={photoPreview} alt="Aperçu" />
                </div>
              )}
            </div>
          </div>

          <div className="form-actions">
            <button 
              type="submit" 
              className="btn btn-primary"
              disabled={loading}
            >
              {loading ? 'Enregistrement...' : 'Enregistrer'}
            </button>
          </div>
        </form>
        
        {onSwitchToLogin && (
          <div className="form-footer">
            <p>Vous avez déjà un compte ?</p>
            <button className="btn-link" onClick={onSwitchToLogin}>
              Se connecter
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default PatientRegistration;
