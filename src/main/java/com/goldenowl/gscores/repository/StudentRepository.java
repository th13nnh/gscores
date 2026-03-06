package com.goldenowl.gscores.repository;

import com.goldenowl.gscores.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    // Query to find top 10 students in Group A (Math, Physics, Chemistry)
    @Query(value = "SELECT * FROM students " +
            "WHERE toan IS NOT NULL AND vat_li IS NOT NULL AND hoa_hoc IS NOT NULL " +
            "ORDER BY (toan + vat_li + hoa_hoc) DESC LIMIT 10",
            nativeQuery = true)
    List<Student> findTop10GroupA();

    // Counts for statistics
    long countByToanGreaterThanEqual(Double score);
    long countByToanLessThanAndToanGreaterThanEqual(Double upper, Double lower);
    long countByToanLessThan(Double score);
}