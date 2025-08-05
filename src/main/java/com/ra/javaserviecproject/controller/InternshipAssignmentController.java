package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.InternshipAssignmentDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.InternshipAssignment;
import com.ra.javaserviecproject.service.InternshipAssignmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
public class InternshipAssignmentController {
    @Autowired
    private InternshipAssignmentService internshipAssignmentService;

    @PostMapping
    public ResponseEntity<APIResponse<InternshipAssignment>> add(@Valid @RequestBody InternshipAssignmentDTO dto) {
        InternshipAssignment newInternshipAssignment = internshipAssignmentService.add(dto);
        return ResponseEntity.status(201).body(
                APIResponse.<InternshipAssignment>builder()
                        .message("Đăng ký phân công thực tập thành công")
                        .data(newInternshipAssignment)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<InternshipAssignment>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<InternshipAssignment>>builder()
                        .message("Lấy danh sách phân công thực tập thành công")
                        .success(true)
                        .data(internshipAssignmentService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<InternshipAssignment>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<InternshipAssignment>builder()
                        .message("Lấy thông tin phân công thực tập thành công")
                        .success(true)
                        .data(internshipAssignmentService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<InternshipAssignment>> update(@PathVariable Integer id, @Valid @RequestBody InternshipAssignmentDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<InternshipAssignment>builder()
                        .message("Cập nhập phân công thực tập thành công")
                        .success(true)
                        .data(internshipAssignmentService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable Integer id) {
        internshipAssignmentService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa phân công thực tập dùng thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}