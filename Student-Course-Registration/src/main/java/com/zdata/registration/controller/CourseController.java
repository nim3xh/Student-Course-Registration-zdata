package com.zdata.registration.controller;

import com.zdata.registration.dto.*;
import com.zdata.registration.service.CourseService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> addCourse(@Valid @RequestBody CourseRequestDTO request) {
        return ResponseEntity.ok(courseService.addCourse(request));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
