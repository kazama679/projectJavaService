package com.ra.javaserviecproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessmentRounds")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roundId;

    @ManyToOne
    @JoinColumn(name = "phaseId", nullable = false)
    private InternshipPhase phase;

    @Column(nullable = false, length = 100)
    private String roundName;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
