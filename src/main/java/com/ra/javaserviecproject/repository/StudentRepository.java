package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    boolean existsByStudentCode(String studentCode);
    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.studentCode = :studentCode AND s.studentId != :studentId")
    boolean existsByStudentCodeAndStudentIdNot(@Param("studentCode") String studentCode,
                                               @Param("studentId") Integer studentId);
}
