# Student Course Registration System

## How to Run the Application

1. Prerequisites:
   - Java JDK 17 or later
   - Maven 3.8.6 or later

2. Build and Run:
   mvn clean install
   mvn spring-boot:run

3. Access the API:
   - The application will start on port 8080 by default
   - API base URL: http://localhost:8080

## Sample HTTP Requests/Responses

### Course Management

Create a new course:
curl -X POST http://localhost:8080/courses \
-H "Content-Type: application/json" \
-d '{
    "code": "CS101",
    "title": "Introduction to Computer Science",
    "instructor": "Dr. Smith"
}'

Response:
{
    "id": 1,
    "code": "CS101",
    "title": "Introduction to Computer Science",
    "instructor": "Dr. Smith"
}

Get all courses:
curl -X GET http://localhost:8080/courses

Response:
[
    {
        "id": 1,
        "code": "CS101",
        "title": "Introduction to Computer Science",
        "instructor": "Dr. Smith"
    }
]

### Student Management

Register a new student:
curl -X POST http://localhost:8080/students \
-H "Content-Type: application/json" \
-d '{
    "name": "John Doe",
    "email": "john.doe@example.com"
}'

Response:
{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com"
}

Register student to course:
curl -X POST http://localhost:8080/students/1/register/1

Response:
"Student registered to course successfully"

Get registered courses for a student:
curl -X GET http://localhost:8080/students/1/courses

Response:
[
    {
        "id": 1,
        "code": "CS101",
        "title": "Introduction to Computer Science",
        "instructor": "Dr. Smith"
    }
]

Drop a course:
curl -X DELETE http://localhost:8080/students/1/drop/1

Response:
"Course dropped successfully"

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

`src/main/java/com/zdata/registration/
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
├── model/                 # Domain models
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
│   └── StudentCourseRegistrationApplicationTests.java
└── StudentCourseRegistrationApplication.java  # Main application class`

## Test Coverage

The application includes unit tests for controllers:

1. CourseControllerTest:
   - Tests adding courses with valid and invalid inputs
   - Tests retrieving all courses
   - Tests duplicate course code scenarios

2. StudentControllerTest:
   - Tests student registration
   - Tests course registration and dropping
   - Tests retrieving registered courses
   - Tests duplicate email scenarios

3. Integration Test:
   - Basic context loading test

Test cases cover:
- Success scenarios with proper responses
- Error scenarios with appropriate exceptions
- Validation of response status codes and content
