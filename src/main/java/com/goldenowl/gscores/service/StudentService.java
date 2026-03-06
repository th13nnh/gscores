package com.goldenowl.gscores.service;

import com.goldenowl.gscores.entity.Student;
import com.goldenowl.gscores.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
    private final StudentRepository repository;
    public StudentService(StudentRepository repository) { this.repository = repository; }

    public Optional<Student> getStudentBySbd(String sbd) { return repository.findById(sbd); }

    public List<Student> getTop10GroupA() { return repository.findTop10GroupA(); }

    public Map<String, Object> getReport() {
        Map<String, Object> report = new HashMap<>();
        String[] subjects = {"toan", "nguVan", "ngoaiNgu", "vatLi", "hoaHoc", "sinhHoc"};

        report.put("levels", List.of(">= 8", "6 to 8", "4 to 6", "< 4"));
        report.put("math_data", List.of(
                repository.countByToanGreaterThanEqual(8.0),
                repository.countByToanLessThanAndToanGreaterThanEqual(8.0, 6.0),
                repository.countByToanLessThanAndToanGreaterThanEqual(6.0, 4.0),
                repository.countByToanLessThan(4.0)
        ));
        return report;
    }
}