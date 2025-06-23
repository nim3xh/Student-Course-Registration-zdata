package com.zdata.registration.repository;

import com.zdata.registration.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    private final Map<UUID, Student> studentMap = new HashMap<>();

    // Finds all students
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    // Finds a student by ID
    public Optional<Student> findById(UUID id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    // Finds a student by their email
    public Optional<Student> findByEmail(String email) {
        return studentMap.values().stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Saves a student, either creating or updating
    public Student save(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    // Deletes a student by their ID
    public void delete(UUID id) {
        studentMap.remove(id);
    }

}