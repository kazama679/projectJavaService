package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.AssessmentRoundDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.AssessmentRound;
import com.ra.javaserviecproject.service.AssessmentRoundService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assessmentsRounds")
public class AssessmentRoundController {
    @Autowired
    private AssessmentRoundService assessmentRoundService;

    @PostMapping
    public ResponseEntity<APIResponse<AssessmentRound>> add(@Valid @RequestBody AssessmentRoundDTO dto) {
        AssessmentRound newAssessmentRound = assessmentRoundService.add(dto);
        return ResponseEntity.status(201).body(
                APIResponse.<AssessmentRound>builder()
                        .message("Đăng ký đợt đánh giá thành công")
                        .data(newAssessmentRound)
                        .success(true).timestamp(LocalDateTime.now())
                        .build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<AssessmentRound>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<AssessmentRound>>builder()
                        .message("Lấy danh sách đợt đánh giá thành công")
                        .success(true)
                        .data(assessmentRoundService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AssessmentRound>> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<AssessmentRound>builder()
                        .message("Lấy thông tin đợt đánh giá thành công")
                        .success(true)
                        .data(assessmentRoundService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<AssessmentRound>> updateUser(@PathVariable Integer id, @Valid @RequestBody AssessmentRoundDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<AssessmentRound>builder()
                        .message("Cập nhập đợt đánh giá thành công")
                        .success(true)
                        .data(assessmentRoundService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
        assessmentRoundService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa đợt đánh giá thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/phase/{phaseId}")
    public ResponseEntity<APIResponse<List<AssessmentRound>>> getByPhase(@PathVariable Integer phaseId) {
        return ResponseEntity.ok(
                APIResponse.<List<AssessmentRound>>builder()
                        .message("Lấy danh sách đợt đánh giá theo giai đoạn thực tập")
                        .success(true)
                        .data(assessmentRoundService.findAllByPhaseId(phaseId))
                        .timestamp(java.time.LocalDateTime.now())
                        .build()
        );
    }
}