package com.ra.javaserviecproject.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MentorDTO {
    @NotNull(message = "ID giảng viên không được để trống")
    private Integer id;
    @Size(max = 100, message = "Tên bộ môn/khoa tối đa 100 ký tự")
    private String department;
    @Size(max = 50, message = "Học hàm/học vị tối đa 50 ký tự")
    private String academicRank;
}
