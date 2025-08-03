package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InternshipPhaseDTO {
    @NotBlank(message = "Tên tiêu chí không được để trống")
    @Size(max = 200, message = "Tên tiêu chí tối đa 200 ký tự")
    private String criterionName;

    @Size(max = 1000, message = "Mô tả tiêu chí tối đa 1000 ký tự")
    private String description;

    @NotNull(message = "Điểm tối đa không được để trống")
    @DecimalMin(value = "0.1", inclusive = true, message = "Điểm tối đa phải lớn hơn 0")
    private BigDecimal maxScore;
}
