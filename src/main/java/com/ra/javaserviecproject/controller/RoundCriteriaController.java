package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.RoundCriterionDTO;
import com.ra.javaserviecproject.model.dto.request.RoundUpdateDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.RoundCriterion;
import com.ra.javaserviecproject.service.RoundCriterionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roundCriterias")
public class RoundCriteriaController {
    @Autowired
    private RoundCriterionService roundCriterionService;

    @PostMapping
    public ResponseEntity<APIResponse<RoundCriterion>> add(@Valid @RequestBody RoundCriterionDTO dto) {
        RoundCriterion newRoundCriterion = roundCriterionService.add(dto);
        return ResponseEntity.status(201).body(
                APIResponse.<RoundCriterion>builder()
                        .message("Đăng ký tiêu chí của đợt đánh giá thành công")
                        .data(newRoundCriterion)
                        .success(true).timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<RoundCriterion>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<RoundCriterion>>builder()
                        .message("Lấy danh sách tiêu chí của đợt đánh giá thành công")
                        .success(true)
                        .data(roundCriterionService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<RoundCriterion>> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<RoundCriterion>builder()
                        .message("Lấy thông tin tiêu chí của đợt đánh giá thành công")
                        .success(true)
                        .data(roundCriterionService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<RoundCriterion>> updateUser(@PathVariable Integer id, @Valid @RequestBody RoundUpdateDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<RoundCriterion>builder()
                        .message("Cập nhập tiêu chí của đợt đánh giá thành công")
                        .success(true)
                        .data(roundCriterionService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
        roundCriterionService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa tiêu chí của đợt đánh giá thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}