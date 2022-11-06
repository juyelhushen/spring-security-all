package com.jskool.security.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class StudentController {

    private static final List<Student> students = Arrays.asList(
            new Student(1,"juyel"),
            new Student(2,"kanan"),
            new Student(3,"jebu")
    );


    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return students.stream().filter(student -> id.equals(student.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("Student "+id+" doesn't exits"));
    }
}
