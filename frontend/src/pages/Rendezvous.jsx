import React, { useState, useEffect } from 'react';
import { rendezVousApi, serviceMedicalApi, dentisteApi } from '../services/api';
import { useAuth } from '../context/AuthContext';

const Rendezvous = () => {
  const { user, userType } = useAuth();
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({
    idD: '',
    premiereVisite: 'non',
    defautSouhait: '',
    dateRv: '',
    heureRv: '',
    descriptionRv: ''
  });

  // Service selection state
  const [availableServices, setAvailableServices] = useState([]);
  const [selectedServiceId, setSelectedServiceId] = useState('');
  const [selectedServices, setSelectedServices] = useState([]);

  // Dentist selection
  const [dentistes, setDentistes] = useState([]);
  const [selectedDentiste, setSelectedDentiste] = useState(null);
  
  // Available time slots
  const [availableHours, setAvailableHours] = useState([]);

  useEffect(() => {
    loadDentistes();
  }, []);

  useEffect(() => {
    if (formData.idD && formData.dateRv) {
      loadOccupiedSlots(formData.idD, formData.dateRv);
    }
  }, [formData.idD, formData.dateRv]);

  useEffect(() => {
    if (selectedDentiste) {
      loadServices(selectedDentiste.specialite);
    }
  }, [selectedDentiste]);

  const loadServices = async (specialite) => {
    try {
      const services = await serviceMedicalApi.getAll();
      
      // Map specialties to service types
      let filteredServices = services;
      if (specialite) {
        const specialityMapping = {
          'Orthodontie': ['CONSULTATION', 'ESTHETIQUE', 'PREVENTION'],
          'Chirurgie Dentaire': ['CHIRURGIE', 'URGENCE', 'CONSULTATION'],
          'Parodontologie': ['SOIN_DENTAIRE', 'PREVENTION', 'CONSULTATION'],
          'Endodontie': ['SOIN_DENTAIRE', 'CONSULTATION'],
          'Implantologie': ['IMPLANTOLOGIE', 'CHIRURGIE', 'CONSULTATION'],
          'Prosthodontie': ['ESTHETIQUE', 'IMPLANTOLOGIE', 'CONSULTATION']
        };
        
        const allowedTypes = specialityMapping[specialite] || ['CONSULTATION'];
        filteredServices = services.filter(service => 
          allowedTypes.includes(service.type)
        );
      }
      
      setAvailableServices(filteredServices);
    } catch (error) {
      console.error('Error loading services:', error);
    }
  };

  const loadDentistes = async () => {
    try {
      const data = await dentisteApi.getAll();
      setDentistes(data);
    } catch (error) {
      console.error('Error loading dentistes:', error);
    }
  };

  const loadOccupiedSlots = async (dentisteId, date) => {
    try {
      // Appel direct au backend pour obtenir les créneaux disponibles
      const availableSlots = await rendezVousApi.getAvailableSlots(dentisteId, date);
      setAvailableHours(availableSlots);
    } catch (error) {
      console.error('Error loading available slots:', error);
      setAvailableHours([]);
    }
  };

  const handleDentisteChange = (e) => {
    const dentisteId = e.target.value;
    setFormData({...formData, idD: dentisteId, heureRv: '', dateRv: ''});
    const dentiste = dentistes.find(d => d.id === parseInt(dentisteId));
    setSelectedDentiste(dentiste);
    // Reset selected services when dentist changes
    setSelectedServices([]);
    setAvailableHours([]);
  };

  const handleAddService = () => {
    if (!selectedServiceId) {
      alert('Veuillez sélectionner un service');
      return;
    }

    const service = availableServices.find(s => s.id === parseInt(selectedServiceId));
    if (service && !selectedServices.find(s => s.id === service.id)) {
      setSelectedServices([...selectedServices, service]);
      setSelectedServiceId('');
    } else if (selectedServices.find(s => s.id === service.id)) {
      alert('Ce service est déjà ajouté');
    }
  };

  const handleRemoveService = (serviceId) => {
    setSelectedServices(selectedServices.filter(s => s.id !== serviceId));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // Vérifier que l'utilisateur est connecté
    if (!user || userType !== 'patient') {
      alert('Vous devez être connecté en tant que patient pour prendre un rendez-vous');
      return;
    }

    if (!formData.idD) {
      alert('Veuillez sélectionner un dentiste');
      return;
    }

    if (selectedServices.length === 0) {
      alert('Veuillez sélectionner au moins un service');
      return;
    }

    try {
      const rendezVousData = {
        idP: user.idP, // ID du patient connecté
        idD: parseInt(formData.idD),
        dateRv: formData.dateRv,
        heureRv: formData.heureRv,
        statutRv: 'EN_ATTENTE',
        descriptionRv: `Première visite: ${formData.premiereVisite}\nDéfaut/Souhait: ${formData.defautSouhait}\n${formData.descriptionRv}\n\nServices: ${selectedServices.map(s => s.nom).join(', ')}`
      };
      
      console.log('Sending rendez-vous data:', rendezVousData);
      await rendezVousApi.create(rendezVousData);
      handleCancel();
      alert('Rendez-vous créé avec succès!');
    } catch (error) {
      console.error('Error creating rendez-vous:', error);
      alert(`Erreur lors de la création du rendez-vous: ${error.message}`);
    }
  };

  const handleCancel = () => {
    setFormData({
      idD: '',
      premiereVisite: 'non',
      defautSouhait: '',
      dateRv: '',
      heureRv: '',
      descriptionRv: ''
    });
    setSelectedServices([]);
    setSelectedServiceId('');
    setSelectedDentiste(null);
    setShowForm(false);
  };

  // Obtenir la date minimale (aujourd'hui)
  const getMinDate = () => {
    const today = new Date();
    return today.toISOString().split('T')[0];
  };

  return (
    <div className="page-container">
      <h2>Prise de Rendez-vous</h2>
      
      {!user || userType !== 'patient' ? (
        <div className="info-card" style={{ backgroundColor: '#fff3cd', border: '1px solid #ffc107' }}>
          <p style={{ margin: 0, color: '#856404' }}>
            ⚠️ Vous devez être connecté en tant que patient pour prendre un rendez-vous.
          </p>
        </div>
      ) : (
        <>
          <button 
            className="btn btn-primary"
            onClick={() => setShowForm(!showForm)}
          >
            {showForm ? 'Fermer' : 'Nouveau Rendez-vous'}
          </button>

          {showForm && (
            <div className="form-card">
              <h3>Réserver un rendez-vous</h3>
              <form onSubmit={handleSubmit}>
                {/* Patient Info Display */}
                <div style={{ 
                  padding: '15px', 
                  backgroundColor: '#e7f3ff', 
                  borderRadius: '8px',
                  marginBottom: '20px'
                }}>
                  <p style={{ margin: 0, fontWeight: 'bold', color: '#0066cc' }}>
                    Patient: {user.nomP} {user.prenomP}
                  </p>
                </div>

                {/* Dentist Selection */}
                <div className="form-group">
                  <label>Choisir un dentiste: *</label>
                  <select
                    value={formData.idD}
                    onChange={handleDentisteChange}
                    className="form-control"
                    required
                  >
                    <option value="">-- Sélectionner un dentiste --</option>
                    {dentistes.map(dentiste => (
                      <option key={dentiste.id} value={dentiste.id}>
                        Dr. {dentiste.nom} {dentiste.prenom} - {dentiste.specialite}
                      </option>
                    ))}
                  </select>
                  {selectedDentiste && (
                    <small className="form-text text-muted">
                      Spécialité: {selectedDentiste.specialite} | Téléphone: {selectedDentiste.tel}
                    </small>
                  )}
                </div>

                {/* Appointment Information Section */}
                <div style={{ 
                  marginTop: '20px', 
                  padding: '15px', 
                  border: '1px solid #ddd', 
                  borderRadius: '5px',
                  backgroundColor: '#f8f9fa'
                }}>
                  <h4 style={{ marginTop: 0, color: '#0066cc' }}>Informations de rendez-vous</h4>
                  
                  <div className="form-row">
                    <div className="form-group">
                      <label>Première visite: *</label>
                      <div style={{ display: 'flex', gap: '20px', marginTop: '8px' }}>
                        <label style={{ display: 'flex', alignItems: 'center', fontWeight: 'normal' }}>
                          <input
                            type="radio"
                            value="oui"
                            checked={formData.premiereVisite === 'oui'}
                            onChange={(e) => setFormData({...formData, premiereVisite: e.target.value})}
                            style={{ marginRight: '5px' }}
                          />
                          Oui
                        </label>
                        <label style={{ display: 'flex', alignItems: 'center', fontWeight: 'normal' }}>
                          <input
                            type="radio"
                            value="non"
                            checked={formData.premiereVisite === 'non'}
                            onChange={(e) => setFormData({...formData, premiereVisite: e.target.value})}
                            style={{ marginRight: '5px' }}
                          />
                          Non
                        </label>
                      </div>
                    </div>
                  </div>

                  <div className="form-group">
                    <label>Défaut constaté / Souhait: *</label>
                    <input
                      type="text"
                      value={formData.defautSouhait}
                      onChange={(e) => setFormData({...formData, defautSouhait: e.target.value})}
                      className="form-control"
                      placeholder="Ex: Douleur dentaire, blanchiment, etc."
                      required
                    />
                  </div>

                  <div className="form-row">
                    <div className="form-group">
                      <label>Date de réservation: *</label>
                      <input
                        type="date"
                        value={formData.dateRv}
                        onChange={(e) => setFormData({...formData, dateRv: e.target.value, heureRv: ''})}
                        className="form-control"
                        min={getMinDate()}
                        required
                        disabled={!formData.idD}
                      />
                      {!formData.idD && (
                        <small className="form-text text-muted">
                          Veuillez d'abord sélectionner un dentiste
                        </small>
                      )}
                    </div>
                    
                    <div className="form-group">
                      <label>Heure: *</label>
                      <select
                        value={formData.heureRv}
                        onChange={(e) => setFormData({...formData, heureRv: e.target.value})}
                        className="form-control"
                        required
                        disabled={!formData.dateRv}
                      >
                        <option value="">-- Choisir une heure --</option>
                        {availableHours.map(hour => (
                          <option key={hour} value={hour}>{hour}</option>
                        ))}
                      </select>
                      {formData.dateRv && availableHours.length === 0 && (
                        <small className="form-text" style={{ color: '#dc3545' }}>
                          Aucun créneau disponible pour cette date
                        </small>
                      )}
                      {formData.dateRv && availableHours.length > 0 && (
                        <small className="form-text text-muted">
                          {availableHours.length} créneaux disponibles
                        </small>
                      )}
                    </div>
                  </div>
                </div>

            {/* Service Selection Section */}
            <div style={{ 
              marginTop: '20px', 
              padding: '15px', 
              border: '1px solid #ddd', 
              borderRadius: '5px',
              backgroundColor: '#f0f8ff'
            }}>
              <h4 style={{ marginTop: 0, color: '#0066cc' }}>Choix de services dentaires</h4>
              
              <div className="form-row" style={{ alignItems: 'flex-end' }}>
                <div className="form-group" style={{ flex: 1 }}>
                  <label>Liste des services:</label>
                  <select
                    value={selectedServiceId}
                    onChange={(e) => setSelectedServiceId(e.target.value)}
                    className="form-control"
                  >
                    <option value="">-- Sélectionner un service --</option>
                    {availableServices.map(service => (
                      <option key={service.id} value={service.id}>
                        {service.nom} - {service.tarif} DT ({service.type})
                      </option>
                    ))}
                  </select>
                </div>
                
                <div style={{ display: 'flex', gap: '10px', marginBottom: '10px' }}>
                  <button 
                    type="button" 
                    className="btn btn-success"
                    onClick={handleAddService}
                    style={{ padding: '8px 20px' }}
                  >
                    &gt;&gt;&gt;
                  </button>
                </div>
              </div>

              <div className="form-group" style={{ marginTop: '15px' }}>
                <label>Services retenus:</label>
                <div style={{ 
                  border: '1px solid #ccc', 
                  borderRadius: '4px', 
                  padding: '10px',
                  minHeight: '100px',
                  backgroundColor: 'white'
                }}>
                  {selectedServices.length === 0 ? (
                    <p style={{ color: '#999', margin: 0 }}>Aucun service sélectionné</p>
                  ) : (
                    <ul style={{ margin: 0, paddingLeft: '20px' }}>
                      {selectedServices.map(service => (
                        <li key={service.id} style={{ 
                          marginBottom: '8px',
                          display: 'flex',
                          justifyContent: 'space-between',
                          alignItems: 'center'
                        }}>
                          <span>
                            <strong>{service.nom}</strong> - {service.tarif} DT 
                            <span style={{ color: '#666', fontSize: '0.9em' }}>
                              {' '}({service.type})
                            </span>
                          </span>
                          <button
                            type="button"
                            className="btn btn-danger"
                            onClick={() => handleRemoveService(service.id)}
                            style={{ 
                              padding: '4px 12px', 
                              fontSize: '0.85em',
                              marginLeft: '10px'
                            }}
                          >
                            &lt;&lt;&lt;
                          </button>
                        </li>
                      ))}
                    </ul>
                  )}
                </div>
              </div>
            </div>

            {/* Additional Details Section */}
            <div style={{ marginTop: '20px' }}>
              <div className="form-group">
                <label>Détails supplémentaires:</label>
                <textarea
                  value={formData.descriptionRv}
                  onChange={(e) => setFormData({...formData, descriptionRv: e.target.value})}
                  className="form-control"
                  rows="4"
                  placeholder="Notes ou informations complémentaires..."
                />
              </div>
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
        </>
      )}

      <div className="info-card">
        <h3>Calendrier des rendez-vous</h3>
        <p>Réservez votre rendez-vous et sélectionnez les services dentaires souhaités.</p>
        <p>Vous pouvez choisir plusieurs services lors d'une même visite.</p>
        <p style={{ marginTop: '15px', padding: '10px', backgroundColor: '#e7f3ff', borderRadius: '5px' }}>
          💡 <strong>Astuce:</strong> Les créneaux horaires disponibles sont automatiquement filtrés en fonction du dentiste et de la date sélectionnés.
        </p>
      </div>
    </div>
  );
};

export default Rendezvous;
