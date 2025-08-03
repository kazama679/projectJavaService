package com.ra.javaserviecproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessmentResults", uniqueConstraints = @UniqueConstraint(columnNames = {"assignmentId", "roundId", "criterionId"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    @ManyToOne
    @JoinColumn(name = "assignmentId", nullable = false)
    private InternshipAssignment assignment;

    @ManyToOne
    @JoinColumn(name = "roundId", nullable = false)
    private AssessmentRound round;

    @ManyToOne
    @JoinColumn(name = "criterionId", nullable = false)
    private EvaluationCriterion criterion;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal score;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "evaluatedBy", nullable = false)
    private User evaluatedBy;

    private LocalDateTime evaluationDate = LocalDateTime.now();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
