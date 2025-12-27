import React, { useState } from 'react';
import { authApi } from '../services/api';
import { useAuth } from '../context/AuthContext';

const AideSoignantLogin = ({ onSwitchToRegister, setActivePage }) => {
  const [formData, setFormData] = useState({
    email: '',
    mdp: ''
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const aideSoignant = await authApi.loginAideSoignant(formData.email, formData.mdp);
      login(aideSoignant, 'aidesoignant');
      setActivePage('publication-form');
    } catch (error) {
      console.error('Login error:', error);
      setError(error.message || 'Email ou mot de passe incorrect');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h2>Connexion Aide-soignant</h2>
        <p className="subtitle">Accédez à votre espace professionnel</p>
        
        {error && <div className="alert alert-error">{error}</div>}
        
        <form onSubmit={handleSubmit} className="login-form">
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

          <button type="submit" className="btn-submit" disabled={loading}>
            {loading ? 'Connexion...' : 'Se connecter'}
          </button>
        </form>

        <div className="form-footer">
          <p>Pas encore de compte ?</p>
          <button className="btn-link" onClick={onSwitchToRegister}>
            S'inscrire
          </button>
        </div>
      </div>
    </div>
  );
};

export default AideSoignantLogin;
