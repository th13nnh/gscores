package com.goldenowl.gscores.service;

import com.goldenowl.gscores.entity.Student;
import com.goldenowl.gscores.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final EntityManager entityManager; // Add this for custom queries

    public StudentService(StudentRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public Optional<Student> getStudentBySbd(String sbd) {
        return repository.findById(sbd);
    }

    public List<Student> getTop10GroupA() {
        return repository.findTop10GroupA();
    }

    public Map<String, Object> getReport() {
        Map<String, Object> report = new HashMap<>();

        // 1. List of database column names
        String[] subjects = {
                "toan", "ngu_van", "ngoai_ngu",
                "vat_li", "hoa_hoc", "sinh_hoc",
                "lich_su", "dia_li", "gdcd"
        };

        // 2. Matching display names for the Chart
        String[] displayNames = {
                "Math", "Literature", "English",
                "Physics", "Chemistry", "Biology",
                "History", "Geography", "Civic Edu"
        };

        report.put("subjects", displayNames);

        List<Long> level1 = new ArrayList<>(); // >= 8
        List<Long> level2 = new ArrayList<>(); // 6 to 8
        List<Long> level3 = new ArrayList<>(); // 4 to 6
        List<Long> level4 = new ArrayList<>(); // < 4

        for (String sub : subjects) {
            String sql = "SELECT " +
                    "COUNT(CASE WHEN " + sub + " >= 8 THEN 1 END), " +
                    "COUNT(CASE WHEN " + sub + " >= 6 AND " + sub + " < 8 THEN 1 END), " +
                    "COUNT(CASE WHEN " + sub + " >= 4 AND " + sub + " < 6 THEN 1 END), " +
                    "COUNT(CASE WHEN " + sub + " < 4 THEN 1 END) " +
                    "FROM students";

            jakarta.persistence.Query query = entityManager.createNativeQuery(sql);
            Object[] results = (Object[]) query.getSingleResult();

            level1.add(((Number) results[0]).longValue());
            level2.add(((Number) results[1]).longValue());
            level3.add(((Number) results[2]).longValue());
            level4.add(((Number) results[3]).longValue());
        }

        report.put("level1", level1);
        report.put("level2", level2);
        report.put("level3", level3);
        report.put("level4", level4);

        return report;
    }
}