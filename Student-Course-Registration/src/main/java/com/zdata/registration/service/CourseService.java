package com.zdata.registration.service;

import com.zdata.registration.dto.CourseRequestDTO;
import com.zdata.registration.dto.CourseResponseDTO;
import com.zdata.registration.model.Course;
import com.zdata.registration.repository.CourseRepository;
import com.zdata.registration.exception.ConflictException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public CourseResponseDTO addCourse(CourseRequestDTO dto) {
        courseRepo.findByCode(dto.getCode()).ifPresent(c -> {
            throw new ConflictException("Course code already exists.");
        });

        Course course = new Course();
        course.setId(UUID.randomUUID());
        course.setCode(dto.getCode());
        course.setTitle(dto.getTitle());
        course.setInstructor(dto.getInstructor());

        courseRepo.save(course);
        return new CourseResponseDTO(course.getId(), course.getCode(), course.getTitle(), course.getInstructor());
    }

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepo.findAll().stream()
                .map(c -> new CourseResponseDTO(c.getId(), c.getCode(), c.getTitle(), c.getInstructor()))
                .collect(Collectors.toList());
    }
}
