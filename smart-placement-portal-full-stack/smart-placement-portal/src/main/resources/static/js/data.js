// =====================================================================
// data.js — Sample Data for Smart Placement Portal
// This file defines all initial data that gets loaded into LocalStorage.
// =====================================================================

const SAMPLE_DATA = {

  students: [
    { id: 1,  name: 'Arjun Sharma',   email: 'omyadav@gmail.com',  password: 'student123', phone: '9876543210', branch: 'CSE',   cgpa: 8.5, skills: 'Java, Spring Boot, MySQL, React',              resume: 'arjun_resume.pdf',  rollNo: 'CSE2021001' },
    { id: 2,  name: 'Priya Patel',    email: 'priya@gmail.com',    password: 'priya123',   phone: '9876543211', branch: 'IT',    cgpa: 7.8, skills: 'Python, Django, HTML, CSS, JavaScript',        resume: 'priya_resume.pdf',  rollNo: 'IT2021002'  },
    { id: 3,  name: 'Rohit Kumar',    email: 'rohit@gmail.com',    password: 'rohit123',   phone: '9876543212', branch: 'ECE',   cgpa: 6.9, skills: 'C, C++, VLSI, Embedded Systems',             resume: 'rohit_resume.pdf',  rollNo: 'ECE2021003' },
    { id: 4,  name: 'Sneha Reddy',    email: 'sneha@gmail.com',    password: 'sneha123',   phone: '9876543213', branch: 'CSE',   cgpa: 9.1, skills: 'Machine Learning, Python, TensorFlow, NLP',   resume: 'sneha_resume.pdf',  rollNo: 'CSE2021004' },
    { id: 5,  name: 'Vikram Singh',   email: 'vikram@gmail.com',   password: 'vikram123',  phone: '9876543214', branch: 'MECH',  cgpa: 7.2, skills: 'AutoCAD, SolidWorks, ANSYS, Matlab',          resume: 'vikram_resume.pdf', rollNo: 'MECH2021005'},
    { id: 6,  name: 'Ananya Iyer',    email: 'ananya@gmail.com',   password: 'ananya123',  phone: '9876543215', branch: 'AI-DS', cgpa: 8.9, skills: 'Data Science, R, Python, Tableau, Power BI', resume: 'ananya_resume.pdf', rollNo: 'AIDS2021006'},
    { id: 7,  name: 'Karan Mehta',    email: 'karan@gmail.com',    password: 'karan123',   phone: '9876543216', branch: 'CSE',   cgpa: 7.5, skills: 'Node.js, React, MongoDB, Express.js',         resume: 'karan_resume.pdf',  rollNo: 'CSE2021007' },
    { id: 8,  name: 'Divya Nair',     email: 'divya@gmail.com',    password: 'divya123',   phone: '9876543217', branch: 'IT',    cgpa: 8.2, skills: 'Java, Android, Kotlin, Firebase',             resume: 'divya_resume.pdf',  rollNo: 'IT2021008'  },
    { id: 9,  name: 'Rahul Gupta',    email: 'rahul@gmail.com',    password: 'rahul123',   phone: '9876543218', branch: 'CSE',   cgpa: 6.5, skills: 'PHP, Laravel, MySQL, HTML, CSS',              resume: 'rahul_resume.pdf',  rollNo: 'CSE2021009' },
    { id: 10, name: 'Meera Joshi',    email: 'meera@gmail.com',    password: 'meera123',   phone: '9876543219', branch: 'AI-DS', cgpa: 9.3, skills: 'Deep Learning, PyTorch, Computer Vision, OpenCV', resume: 'meera_resume.pdf', rollNo: 'AIDS2021010' }
  ],

  placementCellUsers: [
    { id: 1, name: 'Dr. Rajesh Verma',  email: 'admin@gmail.com',        password: 'admin123',  role: 'Placement Officer'       },
    { id: 2, name: 'Prof. Sunita Rao',  email: 'sunita@college.com',     password: 'sunita123', role: 'Training & Placement'    }
  ],

  companies: [
    { id: 1, name: 'TCS',   industry: 'Information Technology', location: 'Bangalore', website: 'www.technova.com',   description: 'TCS is a leading IT solutions company specializing in cloud computing and AI-driven enterprise software. Founded in 2010, it serves 200+ Fortune 500 clients globally.',                       logo: 'TN', color: '#4361ee' },
    { id: 2, name: 'Capgemini', industry: 'Software Development',   location: 'Hyderabad', website: 'www.codesphere.io',  description: 'Capgemini builds cutting-edge web and mobile applications for global startups and enterprises. Known for agile development and innovative tech stacks.',                               logo: 'CS', color: '#7209b7' },
    { id: 3, name: 'Infosys',   industry: 'Data Analytics',         location: 'Pune',      website: 'www.datamind.ai',   description: 'Infosys is an AI and data analytics firm helping businesses make data-driven decisions through advanced ML models and BI dashboards.',                                                  logo: 'DM', color: '#f72585' },
    { id: 4, name: 'Wipro',  industry: 'Cloud Services',         location: 'Mumbai',    website: 'www.cloudcore.tech', description: 'Wipro provides scalable cloud infrastructure, DevOps solutions, and managed services to enterprises across India and Southeast Asia.',                                           logo: 'CC', color: '#4cc9f0' },
    { id: 5, name: 'Accenture',  industry: 'Product Engineering',    location: 'Chennai',   website: 'www.innovatex.in',  description: 'Accenture is a product engineering company building next-generation SaaS platforms. Specializes in React, microservices, and distributed systems.', logo: 'IX', color: '#06d6a0' }
  ],

  drives: [
    { id: 1, companyId: 1, companyName: 'TCS',   jobRole: 'Java Developer',       package: '8 LPA',   minCgpa: 7.0, eligibleBranches: ['CSE','IT','AI-DS'],     location: 'Bangalore', driveDate: '2024-12-15', deadline: '2024-12-01', description: 'We are looking for passionate Java developers to join our backend engineering team. You will work on microservices, REST APIs, and cloud-native applications using Java 21 and Spring Boot.',               status: 'Active', openings: 25 },
    { id: 2, companyId: 2, companyName: 'Capgemini', jobRole: 'Full Stack Developer', package: '7.5 LPA', minCgpa: 6.5, eligibleBranches: ['CSE','IT'],              location: 'Hyderabad', driveDate: '2024-12-20', deadline: '2024-12-05', description: 'Join Capgemini as a Full Stack Developer. Work with React, Node.js, and modern web technologies to build scalable web applications for global clients.',                                                status: 'Active', openings: 15 },
    { id: 3, companyId: 3, companyName: 'Infosys',   jobRole: 'Data Analyst',         package: '9 LPA',   minCgpa: 8.0, eligibleBranches: ['CSE','IT','AI-DS'],     location: 'Pune',      driveDate: '2024-12-18', deadline: '2024-12-03', description: 'Infosys is hiring Data Analysts who can transform raw data into actionable business insights using Python, SQL, and visualization tools like Tableau and Power BI.',                                  status: 'Active', openings: 10 },
    { id: 4, companyId: 4, companyName: 'Wipro',  jobRole: 'DevOps Engineer',      package: '10 LPA',  minCgpa: 7.5, eligibleBranches: ['CSE','IT','ECE'],       location: 'Mumbai',    driveDate: '2024-12-22', deadline: '2024-12-08', description: 'Wipro is seeking DevOps Engineers to manage cloud infrastructure on AWS/Azure, implement CI/CD pipelines, and ensure 99.9% uptime for enterprise clients.',                                    status: 'Active', openings: 8  },
    { id: 5, companyId: 5, companyName: 'Accenture',  jobRole: 'AI/ML Intern',         package: '6 LPA',   minCgpa: 8.5, eligibleBranches: ['CSE','AI-DS'],          location: 'Chennai',   driveDate: '2024-12-25', deadline: '2024-12-10', description: 'Accenture offers an exciting AI/ML internship for top students. Work on real-world ML models, NLP systems, and computer vision projects with our expert team.',                                   status: 'Active', openings: 5  }
  ],

  applications: [
    { id: 1, studentId: 1, driveId: 1, companyName: 'TCS',   jobRole: 'Java Developer',       package: '8 LPA',   appliedDate: '2024-11-20', status: 'Shortlisted' },
    { id: 2, studentId: 1, driveId: 3, companyName: 'Infosys',   jobRole: 'Data Analyst',         package: '9 LPA',   appliedDate: '2024-11-22', status: 'Applied'     },
    { id: 3, studentId: 2, driveId: 2, companyName: 'Capgemini', jobRole: 'Full Stack Developer', package: '7.5 LPA', appliedDate: '2024-11-21', status: 'Interview'   },
    { id: 4, studentId: 4, driveId: 5, companyName: 'Accenture',  jobRole: 'AI/ML Intern',         package: '6 LPA',   appliedDate: '2024-11-19', status: 'Selected'    },
    { id: 5, studentId: 6, driveId: 3, companyName: 'Infosys',   jobRole: 'Data Analyst',         package: '9 LPA',   appliedDate: '2024-11-20', status: 'Selected'    },
    { id: 6, studentId: 7, driveId: 2, companyName: 'Capgemini', jobRole: 'Full Stack Developer', package: '7.5 LPA', appliedDate: '2024-11-23', status: 'Rejected'    },
    { id: 7, studentId: 3, driveId: 4, companyName: 'Wipro',  jobRole: 'DevOps Engineer',      package: '10 LPA',  appliedDate: '2024-11-24', status: 'Applied'     },
    { id: 8, studentId: 8, driveId: 1, companyName: 'TCS',   jobRole: 'Java Developer',       package: '8 LPA',   appliedDate: '2024-11-25', status: 'Shortlisted' }
  ],

  placementResults: [
    { id: 1, applicationId: 4, studentId: 4, studentName: 'Sneha Reddy', companyName: 'Accenture', jobRole: 'AI/ML Intern',   finalPackage: '6 LPA', joiningDate: '2025-07-01', result: 'Selected' },
    { id: 2, applicationId: 5, studentId: 6, studentName: 'Ananya Iyer', companyName: 'Infosys',  jobRole: 'Data Analyst',   finalPackage: '9 LPA', joiningDate: '2025-07-15', result: 'Selected' }
  ]
};
