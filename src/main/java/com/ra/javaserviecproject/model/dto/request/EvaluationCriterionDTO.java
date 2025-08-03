package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvaluationCriterionDTO {
    @NotBlank(message = "Tên tiêu chí không được để trống")
    private String name;

    @NotNull(message = "Trọng số không được để trống")
    @Min(value = 1, message = "Trọng số phải lớn hơn hoặc bằng 1")
    @Max(value = 100, message = "Trọng số phải nhỏ hơn hoặc bằng 100")
    private Integer weight;
}
