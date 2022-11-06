package com.jskool.security.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1,"juyel"),
            new Student(2,"kanan"),
            new Student(3,"jebu")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student_write')")
    public void registerStudent() {
        System.out.println("i am in student"+students);
    }
}
