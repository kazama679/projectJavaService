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
@Table(name = "round_criteria", uniqueConstraints = @UniqueConstraint(columnNames = {"roundId", "criterionId"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundCriterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roundCriterionId;

    @ManyToOne
    @JoinColumn(name = "roundId", nullable = false)
    private AssessmentRound round;

    @ManyToOne
    @JoinColumn(name = "criterionId", nullable = false)
    private EvaluationCriteria criterion;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Boolean status;
}
