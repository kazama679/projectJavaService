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
@Table(name = "evaluation_criteria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer criterionId;

    @Column(nullable = false, unique = true, length = 200)
    private String criterionName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal maxScore;
    private boolean status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
