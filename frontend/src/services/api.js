const API_BASE_URL = 'http://localhost:8080/j2eeproject-1.0-SNAPSHOT/api';

// Patient API
export const patientApi = {
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/patients`);
    return response.json();
  },
  
  getById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/patients/${id}`);
    return response.json();
  },
  
  create: async (patient) => {
    const response = await fetch(`${API_BASE_URL}/patients`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(patient),
    });
    
    if (!response.ok) {
      const text = await response.text();
      throw new Error(`HTTP ${response.status}: ${text}`);
    }
    
    return response.json();
  },
  
  update: async (id, patient) => {
    const response = await fetch(`${API_BASE_URL}/patients/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(patient),
    });
    return response.json();
  },
  
  delete: async (id) => {
    await fetch(`${API_BASE_URL}/patients/${id}`, {
      method: 'DELETE',
    });
  },
};

// Dentiste API
export const dentisteApi = {
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/dentistes`);
    return response.json();
  },
  
  getById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/dentistes/${id}`);
    return response.json();
  },
  
  create: async (dentiste) => {
    const response = await fetch(`${API_BASE_URL}/dentistes`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dentiste),
    });
    return response.json();
  },
  
  delete: async (id) => {
    await fetch(`${API_BASE_URL}/dentistes/${id}`, {
      method: 'DELETE',
    });
  },
};

// Service Medical API
export const serviceMedicalApi = {
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/servicemedicals`);
    return response.json();
  },
  
  getById: async (numSM) => {
    const response = await fetch(`${API_BASE_URL}/servicemedicals/${numSM}`);
    return response.json();
  },
  
  create: async (service) => {
    const response = await fetch(`${API_BASE_URL}/servicemedicals`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(service),
    });
    return response.json();
  },
};

// Rendez-vous API
export const rendezVousApi = {
  getByPatient: async (patientId) => {
    const response = await fetch(`${API_BASE_URL}/rendezvous/patient/${patientId}`);
    return response.json();
  },
  
  getByDentiste: async (dentisteId) => {
    const response = await fetch(`${API_BASE_URL}/rendezvous/dentiste/${dentisteId}`);
    return response.json();
  },
  
  getAvailableSlots: async (dentisteId, date) => {
    const response = await fetch(`${API_BASE_URL}/rendezvous/dentiste/${dentisteId}/available-slots?date=${date}`);
    return response.json();
  },
  
  checkAvailability: async (dentisteId, date, time) => {
    const response = await fetch(`${API_BASE_URL}/rendezvous/dentiste/${dentisteId}/check-availability?date=${date}&time=${time}`);
    return response.json();
  },
  
  create: async (rendezVous) => {
    const response = await fetch(`${API_BASE_URL}/rendezvous`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(rendezVous),
    });
    
    if (!response.ok) {
      const errorData = await response.json().catch(() => ({ error: 'Unknown error' }));
      throw new Error(errorData.error || `HTTP ${response.status}`);
    }
    
    return response.json();
  },
};

// Acte Medical API
export const acteMedicalApi = {
  getByRendezVous: async (rendezVousId) => {
    const response = await fetch(`${API_BASE_URL}/actemedicals/RendezVous/${rendezVousId}`);
    return response.json();
  },
  
  create: async (acteMedical) => {
    const response = await fetch(`${API_BASE_URL}/actemedicals`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(acteMedical),
    });
    return response.json();
  },
};

// Aide-Soignant API
export const aideSoignantApi = {
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/aidesoignants`);
    return response.json();
  },
  
  getById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/aidesoignants/${id}`);
    return response.json();
  },
  
  create: async (aideSoignant) => {
    const response = await fetch(`${API_BASE_URL}/aidesoignants`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(aideSoignant),
    });
    
    if (!response.ok) {
      const text = await response.text();
      throw new Error(`HTTP ${response.status}: ${text}`);
    }
    
    return response.json();
  },
  
  update: async (id, aideSoignant) => {
    const response = await fetch(`${API_BASE_URL}/aidesoignants/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(aideSoignant),
    });
    return response.json();
  },
  
  delete: async (id) => {
    await fetch(`${API_BASE_URL}/aidesoignants/${id}`, {
      method: 'DELETE',
    });
  },
};

// Publication API
export const publicationApi = {
  getAll: async () => {
    const response = await fetch(`${API_BASE_URL}/publications`);
    return response.json();
  },
  
  getById: async (id) => {
    const response = await fetch(`${API_BASE_URL}/publications/${id}`);
    return response.json();
  },
  
  create: async (publication) => {
    const response = await fetch(`${API_BASE_URL}/publications`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(publication),
    });
    
    if (!response.ok) {
      const text = await response.text();
      throw new Error(`HTTP ${response.status}: ${text}`);
    }
    
    return response.json();
  },
  
  update: async (id, publication) => {
    const response = await fetch(`${API_BASE_URL}/publications/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(publication),
    });
    return response.json();
  },
  
  delete: async (id) => {
    await fetch(`${API_BASE_URL}/publications/${id}`, {
      method: 'DELETE',
    });
  },
};

// Auth API
export const authApi = {
  loginPatient: async (email, password) => {
    const response = await fetch(`${API_BASE_URL}/auth/patient/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password }),
    });
    
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.error || 'Login failed');
    }
    
    return response.json();
  },

  loginAideSoignant: async (email, password) => {
    const response = await fetch(`${API_BASE_URL}/auth/aidesoignant/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password }),
    });
    
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.error || 'Login failed');
    }
    
    return response.json();
  },
};
