# Student Details CRUD Demo Project

This is a simple CRUD demo project for managing student details. The project is divided into two repositories: one for the front end and one for the back end.

## Student Details Frontend

[GitHub Repository](https://github.com/NimnaKs/Student-details-Page-frontend)

## Student Details Backend

[GitHub Repository](https://github.com/NimnaKs/Student-details-Page-backend.git)

## Getting Started

### Backend Setup

1. Clone the backend repository:
   ```bash
   git clone https://github.com/NimnaKs/Student-details-Page-backend.git
2. Change database credentials in the backend project.

3. Create a database and the Student table. Use the following SQL query:

sql
Copy code
CREATE TABLE Student
(
    student_id     VARCHAR(10)  NOT NULL PRIMARY KEY,
    first_name     VARCHAR(50)  NULL,
    last_name      VARCHAR(50)  NULL,
    address        VARCHAR(255) NULL,
    batch_no       INT          NULL,
    contact_number VARCHAR(10)  NULL,
    email          VARCHAR(255) NULL,
    program        VARCHAR(50)  NULL
);

4. Run the backend server using Apache Tomcat.
   
### Frontend Setup
[Visit Website](https://nimnaks.github.io/Student-details-Page-frontend/)

Explore, view, add, edit, and delete student details using the frontend interface.

### Contributing
Feel free to contribute to the project by opening issues or submitting pull requests.

This README file section now provides clear instructions for setting up the backend, creating the database and table, running the backend server, and exploring the frontend interface. Feel free to incorporate this into your complete README file.
