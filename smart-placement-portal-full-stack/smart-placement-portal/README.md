# Smart Placement Portal

A full-stack Java mini-project for college placement management.

## Stack
- Frontend: HTML, CSS, JavaScript, Bootstrap 5
- Backend: Java 21, Spring Boot 3.2.4, Spring MVC, Spring Data JPA
- Database: MySQL 8
- Passwords: BCrypt hashing

## Demo accounts
Student:
- Email: omyadav@gmail.com
- Password: student123

Placement Cell:
- Email: placement@gmail.com
- Password: placement123

## Run locally
1. Install Java 21 and MySQL 8.
2. Open `src/main/resources/application.properties`.
3. Set `spring.datasource.username` and `spring.datasource.password` to your MySQL credentials.
4. Open the project in VS Code/IntelliJ.
5. Run `SmartPlacementPortalApplication.java` or run `mvn spring-boot:run`.
6. Open `http://localhost:8080`.

The database `smart_placement_db` and tables are created automatically by Hibernate. Demo companies, students, drives and applications are seeded on the first run.

## Important
This is a college/demo application. The backend uses a simple login flow with BCrypt password hashing, but it does not yet use JWT/session-based authorization. For production deployment, add Spring Security/JWT, HTTPS, role-based access control, server-side validation and environment variables for secrets.
