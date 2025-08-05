package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InternshipPhaseDTO {
    @NotBlank(message = "Tên giai đoạn không được để trống")
    @Size(max = 100, message = "Tên giai đoạn không vượt quá 100 ký tự")
    private String phaseName;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private LocalDate endDate;

    @Size(max = 1000, message = "Mô tả không được vượt quá 1000 ký tự")
    private String description;
}
