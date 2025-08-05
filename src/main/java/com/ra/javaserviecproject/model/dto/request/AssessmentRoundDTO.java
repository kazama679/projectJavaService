package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssessmentRoundDTO {
    @NotNull(message = "Phase ID không được để trống")
    private Integer phaseId;

    @NotBlank(message = "Tên vòng phỏng vấn không được để trống")
    @Size(max = 100, message = "Tên vòng phỏng vấn không được vượt quá 100 ký tự")
    private String roundName;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private LocalDate endDate;

    private String description;
}
