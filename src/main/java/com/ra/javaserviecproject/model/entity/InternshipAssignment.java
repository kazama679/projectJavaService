package com.ra.javaserviecproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "internship_assignments", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "phase_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "phase_id", nullable = false)
    private InternshipPhase phase;

    @Column(nullable = false)
    private LocalDateTime assignedDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status = AssignmentStatus.PENDING;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
