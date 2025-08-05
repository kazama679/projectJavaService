package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AssessmentResultDTO {
    @NotNull(message = "ID phân công không được để trống")
    private Integer assignmentId;

    @NotNull(message = "ID đợt đánh giá không được để trống")
    private Integer roundId;

    @NotNull(message = "ID tiêu chí không được để trống")
    private Integer criterionId;

    @NotNull(message = "Điểm không được để trống")
    @DecimalMin(value = "0.0", inclusive = true, message = "Điểm phải >= 0")
    private BigDecimal score;

    @Size(max = 1000, message = "Nhận xét tối đa 1000 ký tự")
    private String comments;

    @NotNull(message = "ID giáo viên đánh giá không được để trống")
    private Integer evaluatedBy;
    private LocalDateTime evaluationDate;
}
