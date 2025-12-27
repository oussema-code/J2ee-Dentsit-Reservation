import React from 'react';
import { useAuth } from '../context/AuthContext';

const Navbar = ({ setActivePage, activePage }) => {
  const { user, userType, logout, isAuthenticated } = useAuth();

  const handleLogout = () => {
    logout();
    setActivePage('connexion');
  };

  return (
    <nav className="navbar">
      <ul className="nav-list">
        <li>
          <button 
            onClick={() => setActivePage('connexion')} 
            className={`nav-link ${activePage === 'connexion' ? 'active' : ''}`}
          >
            Connexion
          </button>
        </li>
        <li>
          <button 
            onClick={() => setActivePage('service')} 
            className={`nav-link ${activePage === 'service' ? 'active' : ''}`}
          >
            Services
          </button>
        </li>
        <li>
          <button 
            onClick={() => setActivePage('rendezvous')} 
            className={`nav-link ${activePage === 'rendezvous' ? 'active' : ''}`}
          >
            Rendez-vous
          </button>
        </li>
        <li>
          <button 
            onClick={() => setActivePage('publication')} 
            className={`nav-link ${activePage === 'publication' ? 'active' : ''}`}
          >
            Publications
          </button>
        </li>
        {isAuthenticated() && (
          <li className="nav-user-info">
            <span className="user-badge">
              {userType === 'patient' ? '👤' : '👨‍⚕️'} {user?.nomP || user?.nomAS} {user?.prenomP || user?.prenomAS}
            </span>
            <button 
              onClick={handleLogout}
              className="nav-link logout-btn"
            >
              Déconnexion
            </button>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
