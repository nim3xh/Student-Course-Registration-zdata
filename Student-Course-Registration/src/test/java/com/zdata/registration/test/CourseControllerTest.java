package com.zdata.registration.test;

import com.zdata.registration.controller.CourseController;
import com.zdata.registration.dto.CourseRequestDTO;
import com.zdata.registration.dto.CourseResponseDTO;
import com.zdata.registration.exception.ConflictException;
import com.zdata.registration.service.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseControllerTest {

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCourse_ShouldReturnCreatedCourse() {
        CourseRequestDTO request = new CourseRequestDTO();
        request.setCode("CS101");
        request.setTitle("Intro to CS");
        request.setInstructor("Prof. Fernando");
        CourseResponseDTO response = new CourseResponseDTO(1, "CS101", "Intro to CS", "Prof. Fernando");

        when(courseService.addCourse(request)).thenReturn(response);

        ResponseEntity<CourseResponseDTO> result = courseController.addCourse(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals("CS101", result.getBody().getCode());
    }

    @Test
    void getAllCourses_ShouldReturnCourseList() {
        List<CourseResponseDTO> mockCourses = List.of(
                new CourseResponseDTO(1, "CS101", "Intro to CS", "Prof. Fernando")
        );

        when(courseService.getAllCourses()).thenReturn(mockCourses);

        ResponseEntity<List<CourseResponseDTO>> result = courseController.getAllCourses();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(1, result.getBody().size());
        assertEquals("CS101", result.getBody().get(0).getCode());
    }

    @Test
    void createCourse_duplicateCode_shouldThrow() {
        CourseRequestDTO request = new CourseRequestDTO();
        request.setCode("CS101");
        request.setTitle("Intro to CS");
        request.setInstructor("Prof. Fernando");

        when(courseService.addCourse(request)).thenThrow(new ConflictException("Course code already exists."));

        Exception exception = assertThrows(ConflictException.class, () -> {
            courseController.addCourse(request);
        });

        assertEquals("Course code already exists.", exception.getMessage());
    }

}
