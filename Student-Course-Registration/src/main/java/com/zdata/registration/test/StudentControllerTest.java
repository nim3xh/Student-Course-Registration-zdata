package com.zdata.registration.controller;

import com.zdata.registration.dto.*;
import com.zdata.registration.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerStudent_ShouldReturnCreatedStudent() {
        StudentRequestDTO request = new StudentRequestDTO();
        request.setName("Nimesh Rangana");
        request.setEmail("nimesh@example.com");
        StudentResponseDTO response = new StudentResponseDTO(1, "Nimesh Rangana", "nimesh@example.com");

        when(studentService.createStudent(request)).thenReturn(response);

        ResponseEntity<StudentResponseDTO> result = studentController.registerStudent(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Nimesh Rangana", result.getBody().getName());
    }

    @Test
    void registerToCourse_ShouldReturnSuccessMessage() {
        Integer studentId = 1;
        Integer courseId = 100;

        doNothing().when(studentService).registerStudentToCourse(studentId, courseId);

        ResponseEntity<String> result = studentController.registerToCourse(studentId, courseId);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Student registered to course successfully", result.getBody());
    }

    @Test
    void dropCourse_ShouldReturnSuccessMessage() {
        Integer studentId = 1;
        Integer courseId = 100;

        doNothing().when(studentService).dropCourse(studentId, courseId);

        ResponseEntity<String> result = studentController.dropCourse(studentId, courseId);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("Course dropped successfully", result.getBody());
    }

    @Test
    void getRegisteredCourses_ShouldReturnCourseList() {
        Integer studentId = 1;
        List<CourseResponseDTO> mockCourses = List.of(
                new CourseResponseDTO(100, "CS101", "Intro to CS", "Prof. Fernando")
        );

        when(studentService.getRegisteredCourses(studentId)).thenReturn(mockCourses);

        ResponseEntity<List<CourseResponseDTO>> result = studentController.getRegisteredCourses(studentId);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());
        assertEquals("CS101", result.getBody().get(0).getCode());
    }
}
