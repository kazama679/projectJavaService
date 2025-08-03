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
@Table(name = "internshipAssignments", uniqueConstraints = @UniqueConstraint(columnNames = {"studentId", "phaseId"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "mentorId", nullable = false)
    private Mentor mentor;

    @ManyToOne
    @JoinColumn(name = "phaseId", nullable = false)
    private InternshipPhase phase;

    @Column(nullable = false)
    private LocalDateTime assignedDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status = AssignmentStatus.PENDING;

    public enum AssignmentStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELLED
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
