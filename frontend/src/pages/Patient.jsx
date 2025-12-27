import React, { useState, useEffect } from 'react';
import { patientApi } from '../services/api';

const Patient = () => {
  const [patients, setPatients] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
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

  useEffect(() => {
    loadPatients();
  }, []);

  const loadPatients = async () => {
    try {
      setLoading(true);
      const data = await patientApi.getAll();
      setPatients(data);
      setError('');
    } catch (error) {
      console.error('Error loading patients:', error);
      setError('Erreur lors du chargement des patients');
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    
    try {
      setLoading(true);
      if (editingId) {
        await patientApi.update(editingId, formData);
        setSuccess('Patient modifié avec succès!');
      } else {
        await patientApi.create(formData);
        setSuccess('Patient créé avec succès!');
      }
      setShowForm(false);
      setEditingId(null);
      resetForm();
      loadPatients();
      setTimeout(() => setSuccess(''), 3000);
    } catch (error) {
      console.error('Error saving patient:', error);
      setError('Erreur lors de l\'enregistrement du patient');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer ce patient ?')) {
      try {
        setLoading(true);
        await patientApi.delete(id);
        setSuccess('Patient supprimé avec succès!');
        loadPatients();
        setTimeout(() => setSuccess(''), 3000);
      } catch (error) {
        console.error('Error deleting patient:', error);
        setError('Erreur lors de la suppression du patient');
      } finally {
        setLoading(false);
      }
    }
  };

  const handleEdit = (patient) => {
    setFormData({
      nom: patient.nom,
      prenom: patient.prenom,
      email: patient.email,
      mdp: '',
      dateNP: patient.dateNP,
      photoP: patient.photoP || '',
      groupSanguinP: patient.groupSanguinP,
      sexeP: patient.sexeP,
      recouvrementP: patient.recouvrementP || ''
    });
    setEditingId(patient.idP);
    setShowForm(true);
  };

  const resetForm = () => {
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
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="page-container">
      <h2>Gestion des Patients</h2>
      
      {error && <div className="alert alert-error">{error}</div>}
      {success && <div className="alert alert-success">{success}</div>}
      
      <button 
        className="btn btn-primary"
        onClick={() => {
          setShowForm(!showForm);
          if (showForm) {
            setEditingId(null);
            resetForm();
          }
        }}
      >
        {showForm ? 'Annuler' : 'Nouveau Patient'}
      </button>

      {showForm && (
        <div className="form-card">
          <h3>{editingId ? 'Modifier Patient' : 'Nouveau Patient'}</h3>
          <form onSubmit={handleSubmit}>
            <div className="form-row">
              <div className="form-group">
                <label>Nom *:</label>
                <input
                  type="text"
                  name="nom"
                  value={formData.nom}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
              
              <div className="form-group">
                <label>Prénom *:</label>
                <input
                  type="text"
                  name="prenom"
                  value={formData.prenom}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Email *:</label>
                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
              
              <div className="form-group">
                <label>Mot de passe *:</label>
                <input
                  type="password"
                  name="mdp"
                  value={formData.mdp}
                  onChange={handleChange}
                  className="form-control"
                  required={!editingId}
                  placeholder={editingId ? "Laisser vide pour ne pas changer" : ""}
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Date de naissance:</label>
                <input
                  type="date"
                  name="dateNP"
                  value={formData.dateNP}
                  onChange={handleChange}
                  className="form-control"
                />
              </div>
              
              <div className="form-group">
                <label>Sexe *:</label>
                <select
                  name="sexeP"
                  value={formData.sexeP}
                  onChange={handleChange}
                  className="form-control"
                >
                  <option value="M">Masculin</option>
                  <option value="F">Féminin</option>
                </select>
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label>Groupe sanguin *:</label>
                <select
                  name="groupSanguinP"
                  value={formData.groupSanguinP}
                  onChange={handleChange}
                  className="form-control"
                >
                  <option value="A">A</option>
                  <option value="B">B</option>
                  <option value="AB">AB</option>
                  <option value="O">O</option>
                </select>
              </div>
              
              <div className="form-group">
                <label>Recouvrement:</label>
                <input
                  type="text"
                  name="recouvrementP"
                  value={formData.recouvrementP}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Informations de recouvrement"
                />
              </div>
            </div>

            <div className="form-group">
              <label>Photo (URL):</label>
              <input
                type="text"
                name="photoP"
                value={formData.photoP}
                onChange={handleChange}
                className="form-control"
                placeholder="URL de la photo"
              />
            </div>

            <div className="form-actions">
              <button type="submit" className="btn btn-success" disabled={loading}>
                {loading ? 'Enregistrement...' : editingId ? 'Modifier' : 'Créer'}
              </button>
              <button 
                type="button" 
                className="btn btn-secondary"
                onClick={() => {
                  setShowForm(false);
                  setEditingId(null);
                  resetForm();
                }}
              >
                Annuler
              </button>
            </div>
          </form>
        </div>
      )}

      <div className="table-container">
        <h3>Liste des Patients ({patients.length})</h3>
        {loading && <div className="loading">Chargement...</div>}
        <table className="data-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Email</th>
              <th>Date Naissance</th>
              <th>Sexe</th>
              <th>Groupe Sanguin</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {patients.length === 0 ? (
              <tr>
                <td colSpan="8" style={{textAlign: 'center'}}>Aucun patient trouvé</td>
              </tr>
            ) : (
              patients.map((patient) => (
                <tr key={patient.idP}>
                  <td>{patient.idP}</td>
                  <td>{patient.nom}</td>
                  <td>{patient.prenom}</td>
                  <td>{patient.email}</td>
                  <td>{patient.dateNP}</td>
                  <td>{patient.sexeP === 'M' ? 'Masculin' : 'Féminin'}</td>
                  <td>{patient.groupSanguinP}</td>
                  <td>
                    <button 
                      className="btn btn-sm btn-warning"
                      onClick={() => handleEdit(patient)}
                      disabled={loading}
                    >
                      Modifier
                    </button>
                    {' '}
                    <button 
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(patient.idP)}
                      disabled={loading}
                    >
                      Supprimer
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Patient;
