package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoundCriterionDTO {
    @NotNull(message = "ID vòng đánh giá không được để trống")
    private Integer roundId;

    @NotNull(message = "ID tiêu chí đánh giá không được để trống")
    private Integer criterionId;
}
