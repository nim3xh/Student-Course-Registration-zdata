# Student Course Registration System

## How to Run the Application

1. Prerequisites:
   - Java JDK 17 or later
   - Maven 3.8.6 or later

2. Build and Run:
```bash
   mvn clean install
   mvn spring-boot:run
```

4. Access the API:
   - The application will start on port 8080 by default
   - API base URL: http://localhost:8080

## Sample HTTP Requests/Responses

### Course Management

Create a new course:
```bash
POST http://localhost:8080/courses
```
```bash
{
    "code": "CS101",
    "title": "Introduction to Computer Science",
    "instructor": "Dr. Subash Jayasinghe"
}
```
```bash
Response:
{
    "id": 1,
    "code": "CS101",
    "title": "Introduction to Computer Science",
    "instructor": "Dr. Subash Jayasinghe"
}
```

Get all courses:
```bash
GET http://localhost:8080/courses
```
```bash
Response:
[
    {
        "id": 1,
        "code": "CS101",
        "title": "Introduction to Computer Science",
        "instructor": "Dr. Subash Jayasinghe"
    }
]
```

### Student Management

Register a new student:
```bash
POST http://localhost:8080/students
```

```bash
{
    "name": "Nimesh Jayaweera",
    "email": "nim3xh@example.com"
}
```
```bash
Response:
{
    "id": 1,
    "name": "Nimesh Jayaweera",
    "email": "nim3xh@example.com"
}
```

Register student to course:
```bash
POST http://localhost:8080/students/1/register/1
```
```bash
Response:
"Student registered to course successfully"
```

Get registered courses for a student:
```bash
GET http://localhost:8080/students/1/courses
```
```bash
Response:
[
    {
        "id": 1,
        "code": "CS101",
        "title": "Introduction to Computer Science",
        "instructor": "Dr. Subash Jayasinghe"
    }
]
```
Drop a course:
```bash
DELETE http://localhost:8080/students/1/drop/1
```
```bash
Response:
"Course dropped successfully"
```
## Assumptions

1. Unique Constraints:
   - Course codes must be unique
   - Student emails must be unique

2. Registration System:
   - A student can register for multiple courses
   - A course can have multiple students registered
   - Students cannot register for the same course more than once

3. Validation:
   - All required fields must be provided
   - Email must be in valid format
   - Student name must be between 2-100 characters

4. Error Handling:
   - Appropriate error messages for not found resources
   - Conflict messages for duplicate registrations
   - Validation error messages for invalid input

## Code Structure
    
```bash
src/main/java/com/zdata/registration/
├── controller/            # REST API endpoints
│   ├── CourseController.java
│   └── StudentController.java
├── dto/                   # Data Transfer Objects
│   ├── CourseRequestDTO.java
│   ├── CourseResponseDTO.java
│   ├── StudentRequestDTO.java
│   └── StudentResponseDTO.java
├── exception/             # Custom exceptions and handler
│   ├── ConflictException.java
│   ├── GlobalExceptionHandler.java
│   └── NotFoundException.java
├── model/                 # Models
│   ├── Course.java
│   ├── Registration.java
│   └── Student.java
├── repository/            # Data access layer
│   ├── CourseRepository.java
│   ├── RegistrationRepository.java
│   └── StudentRepository.java
├── service/               # Business logic
│   ├── CourseService.java
│   └── StudentService.java
├── test/                  # Test classes
│   ├── CourseControllerTest.java
│   ├── StudentControllerTest.java
│ 
└── StudentCourseRegistrationApplication.java  # Main application class
```

## Test Coverage
1. CourseControllerTest:
   - Tests adding courses
   - Tests retrieving all courses
   - Tests duplicate course code scenarios

2. StudentControllerTest:
   - Tests student registration
   - Tests course registration and dropping
   - Tests retrieving registered courses
   - Tests duplicate email scenarios

