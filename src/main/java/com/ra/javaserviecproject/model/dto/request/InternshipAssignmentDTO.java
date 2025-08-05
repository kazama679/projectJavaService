package com.ra.javaserviecproject.model.dto.request;

import com.ra.javaserviecproject.model.entity.AssignmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InternshipAssignmentDTO {
    @NotNull(message = "Sinh viên không được để trống")
    private Integer studentId;
    @Min(value = 1, message = "ID mentor phải lớn hơn 0")
    private Integer mentorId;
    @NotNull(message = "Giai đoạn thực tập không được để trống")
    private Integer phaseId;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
    private LocalDateTime assignedDate;
}
