package com.zdata.registration.controller;

import com.zdata.registration.dto.*;
import com.zdata.registration.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> registerStudent(@Valid @RequestBody StudentRequestDTO request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @PostMapping("/{studentId}/register/{courseId}")
    public ResponseEntity<String> registerToCourse(@PathVariable Integer studentId, @PathVariable UUID courseId) {
        studentService.registerStudentToCourse(studentId, courseId);
        return ResponseEntity.ok("Student registered to course successfully");
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public ResponseEntity<String> dropCourse(@PathVariable Integer studentId, @PathVariable UUID courseId) {
        studentService.dropCourse(studentId, courseId);
        return ResponseEntity.ok("Course dropped successfully");
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseResponseDTO>> getRegisteredCourses(@PathVariable Integer studentId) {
        return ResponseEntity.ok(studentService.getRegisteredCourses(studentId));
    }
}
