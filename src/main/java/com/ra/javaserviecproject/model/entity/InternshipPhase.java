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
@Table(name = "internship_phases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer phaseId;
    @Column(nullable = false, unique = true, length = 100)
    private String phaseName;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    private boolean status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
