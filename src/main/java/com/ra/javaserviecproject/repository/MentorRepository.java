package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {
}
