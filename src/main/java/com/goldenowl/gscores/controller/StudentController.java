package com.goldenowl.gscores.controller;

import com.goldenowl.gscores.entity.Student;
import com.goldenowl.gscores.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*") // Allows your React app to talk to this API
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Endpoint: http://localhost:8080/api/students/SEARCH_ID
    @GetMapping("/{sbd}")
    public ResponseEntity<Student> getStudent(@PathVariable String sbd) {
        return studentService.getStudentBySbd(sbd)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/top10")
    public List<Student> getTop10() {
        return studentService.getTop10GroupA();
    }

    @GetMapping("/report")
    public Map<String, Object> getReport() {
        return studentService.getReport();
    }
}