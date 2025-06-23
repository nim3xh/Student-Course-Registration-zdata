# Student Course Registration System

How to Run the Application

Prerequisites:
- Java 17
- Maven

Steps:
1. git clone <repository-url>
2. cd student-course-registration
3. mvn spring-boot:run

The application will start running at: http://localhost:8080

Sample HTTP Requests to Try with Postman

1. Register a Student

POST /students/register
Content-Type: application/json

{
  "name": "Hiruni Dilmika",
  "email": "hiruni@example.com"
}

2. Add a Course

POST /courses/addCourse
Content-Type: application/json

{
  "courseCode": "CS101",
  "title": "Intro to CS",
  "instructorName": "Dr. Silva"
}

3. View All Students

GET /students/allStudents

4. View All Courses

GET /courses/all

5. Register a Student to a Course

POST /students/{studentId}/register/{courseId}

6. Unregister (Drop) a Course

DELETE /students/{studentId}/drop/{courseId}

7. Get Registered Courses for a Student

GET /students/{studentId}/courses

Assumptions Made

- Email must be unique for each student.
- Course code must be unique.
- No seat limits on courses.
- Integer ID is used as the identifier.
- Data is stored in-memory (no database).

Code Structure

├── controller        // REST controllers handling HTTP requests
├── service           // Business logic layer
├── dto               // Data Transfer Objects for requests/responses
├── model             // Entity/data models
├── exception         // Global exception handlers
└── Application.java  // Main Spring Boot application class

Future Improvements

- Add Swagger for API documentation
- Add pagination for listing courses
- Add Docker support for easier deployment

© 2025 ZDATA Innovations – Internship Assessment Project
