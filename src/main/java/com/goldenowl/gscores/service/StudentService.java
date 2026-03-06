package com.goldenowl.gscores.service;

import com.goldenowl.gscores.entity.Student;
import com.goldenowl.gscores.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Business Logic: Find a student by their SBD
    public Optional<Student> getStudentBySbd(String sbd) {
        return repository.findById(sbd);
    }
}