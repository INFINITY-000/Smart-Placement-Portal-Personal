// Smart Placement Portal - REST API helper
const API_BASE = '/api';

async function apiRequest(path, options = {}) {
    const response = await fetch(API_BASE + path, {
        headers: { 'Content-Type': 'application/json', ...(options.headers || {}) },
        ...options
    });
    const text = await response.text();
    let data = {};
    try { data = text ? JSON.parse(text) : {}; } catch { data = { message: text }; }
    if (!response.ok) throw new Error(data.message || 'Request failed');
    return data;
}

function saveSession(user) {
    localStorage.setItem('spp_current_user', JSON.stringify(user));
    localStorage.setItem('spp_user_type', user.userType || 'student');
    if (user.userType === 'student') localStorage.setItem('studentLoggedIn', 'true');
    if (user.userType === 'placement') localStorage.setItem('placementCellLoggedIn', 'true');
}

function getSession() {
    try { return JSON.parse(localStorage.getItem('spp_current_user')); } catch { return null; }
}

function clearSession() {
    ['spp_current_user','spp_user_type','studentLoggedIn','placementCellLoggedIn'].forEach(k => localStorage.removeItem(k));
}
