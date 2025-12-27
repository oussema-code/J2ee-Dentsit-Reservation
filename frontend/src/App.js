import React, { useState } from 'react';
import { AuthProvider } from './context/AuthContext';
import Header from './components/Header';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Connexion from './pages/Connexion';
import PatientRegistration from './pages/PatientRegistration';
import AideSoignant from './pages/AideSoignant';
import Service from './pages/Service';
import Rendezvous from './pages/Rendezvous';
import Publication from './pages/Publication';
import './mesStyles.css';

function App() {
  const [activePage, setActivePage] = useState('connexion');

  const renderPage = () => {
    switch (activePage) {
      case 'connexion':
        return <Connexion setActivePage={setActivePage} />;
      case 'patient':
        return <PatientRegistration />;
      case 'aidesoignant':
        return <AideSoignant />;
      case 'service':
        return <Service />;
      case 'rendezvous':
        return <Rendezvous setActivePage={setActivePage} />;
      case 'publication':
        return <Publication setActivePage={setActivePage} />;
      default:
        return <Connexion setActivePage={setActivePage} />;
    }
  };

  return (
    <AuthProvider>
      <div className="App">
        <Header />
        <Navbar setActivePage={setActivePage} activePage={activePage} />
        <main className="main-content">
          {renderPage()}
        </main>
        <Footer />
      </div>
    </AuthProvider>
  );
}

export default App;
