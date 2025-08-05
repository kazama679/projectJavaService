package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EvaluationCriteriaDTO {
    @NotBlank(message = "Tên tiêu chí không được để trống")
    @Size(max = 200, message = "Tên tiêu chí không được vượt quá 200 ký tự")
    private String criterionName;
    private String description;
    @NotNull(message = "Điểm tối đa không được để trống")
    @DecimalMin(value = "0.00", inclusive = true, message = "Điểm tối đa phải lớn hơn hoặc bằng 0")
    @DecimalMax(value = "100.00", inclusive = true, message = "Điểm tối đa không được vượt quá 100")
    @Digits(integer = 3, fraction = 2, message = "Điểm tối đa không đúng định dạng (ví dụ: 99.99)")
    private BigDecimal maxScore;
}