// =====================================================================
// storage.js — LocalStorage Helper Functions
// All data is stored in LocalStorage under 'spp_' prefixed keys.
// SPP = Smart Placement Portal
// =====================================================================

const KEYS = {
  STUDENTS:          'spp_students',
  COMPANIES:         'spp_companies',
  DRIVES:            'spp_drives',
  APPLICATIONS:      'spp_applications',
  PLACEMENT_CELL:    'spp_placement_cell',
  PLACEMENT_RESULTS: 'spp_placement_results',
  INITIALIZED:       'spp_initialized',
  CURRENT_USER:      'spp_current_user',
  USER_TYPE:         'spp_user_type'
};

// ---- Initialize LocalStorage with sample data (runs only ONCE) ----
function initializeData() {
  if (localStorage.getItem(KEYS.INITIALIZED)) return;
  localStorage.setItem(KEYS.STUDENTS,          JSON.stringify(SAMPLE_DATA.students));
  localStorage.setItem(KEYS.COMPANIES,         JSON.stringify(SAMPLE_DATA.companies));
  localStorage.setItem(KEYS.DRIVES,            JSON.stringify(SAMPLE_DATA.drives));
  localStorage.setItem(KEYS.APPLICATIONS,      JSON.stringify(SAMPLE_DATA.applications));
  localStorage.setItem(KEYS.PLACEMENT_CELL,    JSON.stringify(SAMPLE_DATA.placementCellUsers));
  localStorage.setItem(KEYS.PLACEMENT_RESULTS, JSON.stringify(SAMPLE_DATA.placementResults));
  localStorage.setItem(KEYS.INITIALIZED,       'true');
}

// ---- Generic Helpers ----
function getAll(key) {
  try { return JSON.parse(localStorage.getItem(key)) || []; }
  catch { return []; }
}
function saveAll(key, data) {
  localStorage.setItem(key, JSON.stringify(data));
}
function getNextId(items) {
  if (!items || items.length === 0) return 1;
  return Math.max(...items.map(i => i.id)) + 1;
}

// =====================================================================
// STUDENTS
// =====================================================================
function getStudents() { return getAll(KEYS.STUDENTS); }
function saveStudents(s) { saveAll(KEYS.STUDENTS, s); }

function getStudentById(id) {
  return getStudents().find(s => s.id === parseInt(id));
}
function getStudentByEmail(email) {
  return getStudents().find(s => s.email === email);
}
function updateStudent(updated) {
  const list = getStudents();
  const i = list.findIndex(s => s.id === updated.id);
  if (i !== -1) { list[i] = { ...list[i], ...updated }; saveStudents(list); return true; }
  return false;
}

// =====================================================================
// COMPANIES
// =====================================================================
function getCompanies() { return getAll(KEYS.COMPANIES); }
function saveCompanies(c) { saveAll(KEYS.COMPANIES, c); }

function getCompanyById(id) {
  return getCompanies().find(c => c.id === parseInt(id));
}
function addCompany(company) {
  const list = getCompanies();
  company.id    = getNextId(list);
  company.logo  = company.logo  || company.name.substring(0, 2).toUpperCase();
  const palette = ['#4361ee','#7209b7','#f72585','#4cc9f0','#06d6a0','#ffd166'];
  company.color = company.color || palette[list.length % palette.length];
  list.push(company);
  saveCompanies(list);
  return company;
}
function updateCompany(updated) {
  const list = getCompanies();
  const i = list.findIndex(c => c.id === updated.id);
  if (i !== -1) { list[i] = { ...list[i], ...updated }; saveCompanies(list); return true; }
  return false;
}
function deleteCompany(id) {
  saveCompanies(getCompanies().filter(c => c.id !== parseInt(id)));
}

// =====================================================================
// PLACEMENT DRIVES
// =====================================================================
function getDrives() { return getAll(KEYS.DRIVES); }
function saveDrives(d) { saveAll(KEYS.DRIVES, d); }

function getDriveById(id) {
  return getDrives().find(d => d.id === parseInt(id));
}
function getActiveDrives() {
  return getDrives().filter(d => d.status === 'Active');
}
function addDrive(drive) {
  const list = getDrives();
  drive.id = getNextId(list);
  list.push(drive);
  saveDrives(list);
  return drive;
}
function updateDrive(updated) {
  const list = getDrives();
  const i = list.findIndex(d => d.id === updated.id);
  if (i !== -1) { list[i] = { ...list[i], ...updated }; saveDrives(list); return true; }
  return false;
}
function deleteDrive(id) {
  saveDrives(getDrives().filter(d => d.id !== parseInt(id)));
}

// =====================================================================
// APPLICATIONS
// =====================================================================
function getApplications() { return getAll(KEYS.APPLICATIONS); }
function saveApplications(a) { saveAll(KEYS.APPLICATIONS, a); }

function getApplicationsByStudent(studentId) {
  return getApplications().filter(a => a.studentId === parseInt(studentId));
}
function getApplicationsByDrive(driveId) {
  return getApplications().filter(a => a.driveId === parseInt(driveId));
}
function hasApplied(studentId, driveId) {
  return getApplications().some(
    a => a.studentId === parseInt(studentId) && a.driveId === parseInt(driveId)
  );
}
function addApplication(app) {
  const list = getApplications();
  app.id          = getNextId(list);
  app.appliedDate = new Date().toISOString().split('T')[0];
  app.status      = 'Applied';
  list.push(app);
  saveApplications(list);
  return app;
}
function updateApplicationStatus(id, status) {
  const list = getApplications();
  const i    = list.findIndex(a => a.id === parseInt(id));
  if (i !== -1) {
    list[i].status = status;
    saveApplications(list);
    // Automatically create a placement result when Selected
    if (status === 'Selected') { _addPlacementResult(list[i]); }
    return true;
  }
  return false;
}

// =====================================================================
// PLACEMENT RESULTS
// =====================================================================
function getPlacementResults() { return getAll(KEYS.PLACEMENT_RESULTS); }

function _addPlacementResult(application) {
  const results = getPlacementResults();
  if (results.some(r => r.applicationId === application.id)) return; // no duplicates
  const student = getStudentById(application.studentId);
  results.push({
    id:            getNextId(results),
    applicationId: application.id,
    studentId:     application.studentId,
    studentName:   student ? student.name : 'Unknown',
    companyName:   application.companyName,
    jobRole:       application.jobRole,
    finalPackage:  application.package,
    joiningDate:   '2025-07-01',
    result:        'Selected'
  });
  localStorage.setItem(KEYS.PLACEMENT_RESULTS, JSON.stringify(results));
}

// =====================================================================
// AUTH
// =====================================================================
function setCurrentUser(user, type) {
  localStorage.setItem(KEYS.CURRENT_USER, JSON.stringify(user));
  localStorage.setItem(KEYS.USER_TYPE, type);
}
function getCurrentUser() {
  try { return JSON.parse(localStorage.getItem(KEYS.CURRENT_USER)); }
  catch { return null; }
}
function getUserType() {
  return localStorage.getItem(KEYS.USER_TYPE);
}
function logout() {
  localStorage.removeItem(KEYS.CURRENT_USER);
  localStorage.removeItem(KEYS.USER_TYPE);
}
function isLoggedIn() { return getCurrentUser() !== null; }

// =====================================================================
// STATS HELPERS
// =====================================================================
function getDashboardStats() {
  const apps = getApplications();
  return {
    totalStudents:    getStudents().length,
    totalCompanies:   getCompanies().length,
    activeDrives:     getActiveDrives().length,
    totalApplications: apps.length,
    selected:         apps.filter(a => a.status === 'Selected').length,
    shortlisted:      apps.filter(a => a.status === 'Shortlisted').length,
    rejected:         apps.filter(a => a.status === 'Rejected').length,
    interview:        apps.filter(a => a.status === 'Interview').length
  };
}

function getStudentStats(studentId) {
  const apps = getApplicationsByStudent(studentId);
  return {
    availableDrives: getActiveDrives().length,
    applications:    apps.length,
    shortlisted:     apps.filter(a => a.status === 'Shortlisted' || a.status === 'Interview').length,
    selected:        apps.filter(a => a.status === 'Selected').length
  };
}
