package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.InternshipAssignment;
import com.ra.javaserviecproject.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InternshipAssignmentRepository extends JpaRepository<InternshipAssignment, Integer> {
    @Query("SELECT ia.student FROM InternshipAssignment ia WHERE ia.mentor.mentorId = :mentorId")
    List<Student> findStudentsByMentorId(@Param("mentorId") Integer mentorId);
    boolean existsByMentor_MentorIdAndStudent_StudentId(Integer mentorId, Integer studentId);
}