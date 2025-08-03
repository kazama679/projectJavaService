package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InternshipAssignmentDTO {
    @NotNull(message = "ID sinh viên không được để trống")
    private Integer studentId;

    @NotNull(message = "ID công ty không được để trống")
    private Integer companyId;

    @NotBlank(message = "Tên nhiệm vụ không được để trống")
    private String task;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private String startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private String endDate;
}
