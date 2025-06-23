package com.zdata.registration.service;

import com.zdata.registration.dto.StudentRequestDTO;
import com.zdata.registration.dto.StudentResponseDTO;
import com.zdata.registration.dto.CourseResponseDTO;
import com.zdata.registration.model.Student;
import com.zdata.registration.model.Course;
import com.zdata.registration.model.Registration;
import com.zdata.registration.repository.StudentRepository;
import com.zdata.registration.repository.CourseRepository;
import com.zdata.registration.repository.RegistrationRepository;
import com.zdata.registration.exception.*;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final RegistrationRepository registrationRepo;

    public StudentService(StudentRepository studentRepo, CourseRepository courseRepo, RegistrationRepository registrationRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.registrationRepo = registrationRepo;
    }

    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        studentRepo.findByEmail(dto.getEmail()).ifPresent(s -> {
            throw new ConflictException("Email already registered.");
        });

        Student student = new Student();
        student.setId(UUID.randomUUID());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        studentRepo.save(student);
        return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail());
    }

    public void registerStudentToCourse(UUID studentId, UUID courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        if (registrationRepo.exists(studentId, courseId)) {
            throw new ConflictException("Student already registered to this course.");
        }

        Registration registration = new Registration();
        registration.setStudentId(studentId);
        registration.setCourseId(courseId);
        registration.setRegisteredAt(LocalDateTime.now());

        registrationRepo.save(registration);
    }

    public void dropCourse(UUID studentId, UUID courseId) {
        if (!registrationRepo.exists(studentId, courseId)) {
            throw new ConflictException("Student is not registered in this course.");
        }

        registrationRepo.delete(studentId, courseId);
    }

    public List<CourseResponseDTO> getRegisteredCourses(UUID studentId) {
        studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<UUID> courseIds = registrationRepo.findCoursesByStudentId(studentId);
        return courseIds.stream()
                .map(id -> courseRepo.findById(id)
                        .orElseThrow(() -> new NotFoundException("Course not found")))
                .map(c -> new CourseResponseDTO(c.getId(), c.getCode(), c.getTitle(), c.getInstructor()))
                .collect(Collectors.toList());
    }
}
