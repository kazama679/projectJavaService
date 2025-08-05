package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.AssessmentResultDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.AssessmentResult;
import com.ra.javaserviecproject.service.AssessmentResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assessmentResult")
public class AssessmentResultController {
    @Autowired
    private AssessmentResultService assessmentResultService;

    @PostMapping
    public ResponseEntity<APIResponse<AssessmentResult>> add(@Valid @RequestBody AssessmentResultDTO dto) {
        AssessmentResult newAssessmentResult = assessmentResultService.add(dto);
        return ResponseEntity.status(201).body(
                APIResponse.<AssessmentResult>builder()
                        .message("Đăng ký kết quả đánh giá thành công")
                        .data(newAssessmentResult)
                        .success(true).timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<AssessmentResult>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<AssessmentResult>>builder()
                        .message("Lấy danh sách kết quả đánh giá thành công")
                        .success(true)
                        .data(assessmentResultService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AssessmentResult>> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<AssessmentResult>builder()
                        .message("Lấy thông tin kết quả đánh giá thành công")
                        .success(true)
                        .data(assessmentResultService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<AssessmentResult>> updateUser(@PathVariable Integer id, @Valid @RequestBody AssessmentResultDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<AssessmentResult>builder()
                        .message("Cập nhập kết quả đánh giá thành công")
                        .success(true)
                        .data(assessmentResultService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
        assessmentResultService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa kết quả đánh giá thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}