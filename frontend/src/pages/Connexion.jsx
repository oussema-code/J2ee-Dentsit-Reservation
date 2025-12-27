import React, { useState } from 'react';
import PatientLogin from './PatientLogin';
import PatientRegistration from './PatientRegistration';
import AideSoignantLogin from './AideSoignantLogin';
import AideSoignant from './AideSoignant';

const Connexion = ({ setActivePage }) => {
  const [selectedType, setSelectedType] = useState(null); // 'patient' or 'aidesoignant'
  const [mode, setMode] = useState('login'); // 'login' or 'register'

  if (selectedType === 'patient') {
    if (mode === 'login') {
      return (
        <PatientLogin 
          setActivePage={setActivePage}
          onSwitchToRegister={() => setMode('register')}
        />
      );
    } else {
      return (
        <PatientRegistration 
          setActivePage={setActivePage}
          onSwitchToLogin={() => setMode('login')}
        />
      );
    }
  }

  if (selectedType === 'aidesoignant') {
    if (mode === 'login') {
      return (
        <AideSoignantLogin 
          setActivePage={setActivePage}
          onSwitchToRegister={() => setMode('register')}
        />
      );
    } else {
      return (
        <AideSoignant 
          setActivePage={setActivePage}
          onSwitchToLogin={() => setMode('login')}
        />
      );
    }
  }

  return (
    <div className="connexion-container">
      <div className="connexion-welcome">
        <h2>Bienvenue sur votre plateforme de rendez-vous dentaires</h2>
        <p>Choisissez votre profil pour continuer</p>
      </div>
      
      <div className="connexion-options">
        <div className="connexion-card" onClick={() => { setSelectedType('patient'); setMode('login'); }}>
          <div className="card-icon">👤</div>
          <h3>Patient</h3>
          <p>Connectez-vous ou créez un compte patient pour prendre rendez-vous</p>
          <button className="btn btn-primary">Accéder</button>
        </div>
        
        <div className="connexion-card" onClick={() => { setSelectedType('aidesoignant'); setMode('login'); }}>
          <div className="card-icon">👨‍⚕️</div>
          <h3>Aide-soignant</h3>
          <p>Espace dédié aux professionnels de santé</p>
          <button className="btn btn-primary">Accéder</button>
        </div>
      </div>
    </div>
  );
};

export default Connexion;
