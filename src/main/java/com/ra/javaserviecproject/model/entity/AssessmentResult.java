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
@Table(name = "assessment_results", uniqueConstraints = @UniqueConstraint(columnNames = {"assignmentId", "roundId", "criterionId"}))
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
    private EvaluationCriteria criterion;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal score;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "evaluated_by", nullable = false)
    private Mentor mentor;

    @Column(nullable = false)
    private LocalDateTime evaluationDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Boolean status;
}
