import React, { useState } from 'react';
import Header from './components/Header';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Connexion from './pages/Connexion';
import Patient from './pages/Patient';
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
        return <Connexion />;
      case 'patient':
        return <Patient />;
      case 'aidesoignant':
        return <AideSoignant />;
      case 'service':
        return <Service />;
      case 'rendezvous':
        return <Rendezvous />;
      case 'publication':
        return <Publication />;
      default:
        return <Connexion />;
    }
  };

  return (
    <div className="App">
      <Header />
      <Navbar setActivePage={setActivePage} />
      <main className="main-content">
        {renderPage()}
      </main>
      <Footer />
    </div>
  );
}

export default App;
