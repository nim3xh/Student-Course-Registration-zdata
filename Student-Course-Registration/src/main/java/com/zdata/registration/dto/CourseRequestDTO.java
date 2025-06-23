package com.zdata.registration.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseRequestDTO {
    // Course code should not be blank
    @NotBlank(message = "Course code is required")
    private String code;

    // Title should not be blank
    @NotBlank(message = "Title is required")
    private String title;

    // Instructor should not be blank
    @NotBlank(message = "Instructor is required")
    private String instructor;

    // getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
