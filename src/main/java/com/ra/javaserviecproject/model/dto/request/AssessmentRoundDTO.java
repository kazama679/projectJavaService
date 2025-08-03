package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AssessmentRoundDTO {
    @NotBlank(message = "Tên vòng đánh giá không được để trống")
    private String name;

    @NotNull(message = "ID kỳ thực tập không được để trống")
    private Integer internshipId;
}
