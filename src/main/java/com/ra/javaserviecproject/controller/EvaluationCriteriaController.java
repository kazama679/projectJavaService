package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.EvaluationCriteriaDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.EvaluationCriteria;
import com.ra.javaserviecproject.service.EvaluationCriteriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluations")
public class EvaluationCriteriaController {
    @Autowired
    private EvaluationCriteriaService evaluationCriteriaService;

    @PostMapping
    public ResponseEntity<APIResponse<EvaluationCriteria>> add(@Valid @RequestBody EvaluationCriteriaDTO dto) {
        EvaluationCriteria newEvaluationCriteria = evaluationCriteriaService.add(dto);
        return ResponseEntity.status(201).body(
                APIResponse.<EvaluationCriteria>builder()
                        .message("Đăng ký tiêu chí đánh giá thành công")
                        .data(newEvaluationCriteria)
                        .success(true).timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<EvaluationCriteria>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<EvaluationCriteria>>builder()
                        .message("Lấy danh sách tiêu chí đánh giá thành công")
                        .success(true)
                        .data(evaluationCriteriaService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<EvaluationCriteria>> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<EvaluationCriteria>builder()
                        .message("Lấy thông tin tiêu chí đánh giá thành công")
                        .success(true)
                        .data(evaluationCriteriaService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<EvaluationCriteria>> updateUser(@PathVariable Integer id, @Valid @RequestBody EvaluationCriteriaDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<EvaluationCriteria>builder()
                        .message("Cập nhập tiêu chí đánh giá thành công")
                        .success(true)
                        .data(evaluationCriteriaService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
        evaluationCriteriaService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa tiêu chí đánh giá thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
