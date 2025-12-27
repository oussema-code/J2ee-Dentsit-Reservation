import React, { useState } from 'react';
import { aideSoignantApi } from '../services/api';

const AideSoignant = ({ setActivePage, onSwitchToLogin }) => {
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [photoPreview, setPhotoPreview] = useState(null);
  const [formData, setFormData] = useState({
    nomAS: '',
    prenomAS: '',
    adresseAS: '',
    telephoneAS: '',
    emailAS: '',
    mdpAS: '',
    dateNAS: '',
    diplomeAS: '',
    photoAS: '',
    sexeAS: 'M'
  });

  const availableDiplomes = [
    'Aide en chirurgie bucco-dentaire',
    'Assistance en parodontologie',
    'Aide en pédodontie',
    'Assistance en implantologie',
    'Recouvrement',
    'Médecin de la famille',
    'Remboursement',
    'Santé publique'
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setPhotoPreview(reader.result);
        setFormData(prev => ({ ...prev, photoAS: reader.result }));
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    
    try {
      setLoading(true);
      await aideSoignantApi.create(formData);
      setShowConfirmation(true);
      setFormData({
        nomAS: '',
        prenomAS: '',
        adresseAS: '',
        telephoneAS: '',
        emailAS: '',
        mdpAS: '',
        dateNAS: '',
        diplomeAS: '',
        photoAS: '',
        sexeAS: 'M'
      });
    } catch (error) {
      console.error('Error creating aide-soignant:', error);
      
      // Check if it's a duplicate email error
      const errorMessage = error.message || '';
      if (errorMessage.includes('Duplicate') || errorMessage.includes('UK_')) {
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
          <p>Votre compte aide-soignant a été créé avec succès.</p>
          <p className="encouragement">
            Vous pouvez maintenant vous connecter pour publier des articles et partager votre expertise.
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
    <div className="aidesoignant-container">
      <div className="aidesoignant-card">
        <h2>Inscription Aide-soignant</h2>
        <p className="subtitle">Remplissez le formulaire pour créer votre compte professionnel</p>
        
        {error && <div className="alert alert-error">{error}</div>}
        
        <form onSubmit={handleSubmit} className="aidesoignant-form">
          <div className="form-row">
            <div className="form-group">
              <label>Nom *</label>
              <input
                type="text"
                name="nomAS"
                value={formData.nomAS}
                onChange={handleChange}
                required
                placeholder="Votre nom"
              />
            </div>
            
            <div className="form-group">
              <label>Prénom *</label>
              <input
                type="text"
                name="prenomAS"
                value={formData.prenomAS}
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
                name="emailAS"
                value={formData.emailAS}
                onChange={handleChange}
                required
                placeholder="votre.email@exemple.com"
              />
            </div>
            
            <div className="form-group">
              <label>Mot de passe *</label>
              <input
                type="password"
                name="mdpAS"
                value={formData.mdpAS}
                onChange={handleChange}
                required
                placeholder="••••••••"
              />
            </div>
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Téléphone *</label>
              <input
                type="tel"
                name="telephoneAS"
                value={formData.telephoneAS}
                onChange={handleChange}
                required
                placeholder="+216 XX XXX XXX"
              />
            </div>
            
            <div className="form-group">
              <label>Date de naissance *</label>
              <input
                type="date"
                name="dateNAS"
                value={formData.dateNAS}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="form-group">
            <label>Adresse *</label>
            <input
              type="text"
              name="adresseAS"
              value={formData.adresseAS}
              onChange={handleChange}
              required
              placeholder="Votre adresse complète"
            />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label>Sexe *</label>
              <select
                name="sexeAS"
                value={formData.sexeAS}
                onChange={handleChange}
                required
              >
                <option value="M">Masculin</option>
                <option value="F">Féminin</option>
              </select>
            </div>

            <div className="form-group">
              <label>Diplôme *</label>
              <select
                name="diplomeAS"
                value={formData.diplomeAS}
                onChange={handleChange}
                required
              >
                <option value="">Sélectionnez un diplôme</option>
                {availableDiplomes.map(diplome => (
                  <option key={diplome} value={diplome}>{diplome}</option>
                ))}
              </select>
            </div>
          </div>

          <div className="form-group">
            <label>Photo de profil</label>
            <div className="file-input-wrapper">
              <input
                type="file"
                id="photoAS"
                accept="image/*"
                onChange={handleFileChange}
                className="file-input"
              />
              <label htmlFor="photoAS" className="file-input-label">
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

          <button type="submit" className="btn-submit" disabled={loading}>
            {loading ? 'Enregistrement...' : 'Enregistrer'}
          </button>
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

export default AideSoignant;
