package com.goldenowl.gscores.repository;

import com.goldenowl.gscores.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity Type, Primary Key Type>
public interface StudentRepository extends JpaRepository<Student, String> {
}