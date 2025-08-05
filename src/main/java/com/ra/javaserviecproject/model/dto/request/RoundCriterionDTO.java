package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoundCriterionDTO {
    @NotNull(message = "ID vòng đánh giá không được để trống")
    private Integer roundId;

    @NotNull(message = "ID tiêu chí đánh giá không được để trống")
    private Integer criterionId;

    @NotNull(message = "Trọng số không được để trống")
    @DecimalMin(value = "0.01", inclusive = false, message = "Trọng số phải lớn hơn 0")
    @Digits(integer = 3, fraction = 2, message = "Trọng số tối đa 3 chữ số phần nguyên và 2 chữ số phần thập phân")
    private BigDecimal weight;
}
