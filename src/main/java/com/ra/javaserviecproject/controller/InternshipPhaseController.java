package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.InternshipPhaseDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.InternshipPhase;
import com.ra.javaserviecproject.service.InternshipPhaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/internships")
public class InternshipPhaseController {
    @Autowired
    private InternshipPhaseService internshipPhaseService;

    @PostMapping
    public ResponseEntity<APIResponse<InternshipPhase>> add(@Valid @RequestBody InternshipPhaseDTO internshipPhaseDTO) {
        InternshipPhase newInternshipPhase = internshipPhaseService.add(internshipPhaseDTO);
        return ResponseEntity.status(201).body(
                APIResponse.<InternshipPhase>builder().message("Đăng ký giai đoạn thực tập thành công").data(newInternshipPhase).success(true).timestamp(LocalDateTime.now()).build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<InternshipPhase>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<InternshipPhase>>builder()
                        .message("Lấy danh sách giai đoạn thực tập thành công")
                        .success(true)
                        .data(internshipPhaseService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<InternshipPhase>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<InternshipPhase>builder()
                        .message("Lấy thông tin giai đoạn thực tập thành công")
                        .success(true)
                        .data(internshipPhaseService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<InternshipPhase>> update(@PathVariable Integer id, @Valid @RequestBody InternshipPhaseDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<InternshipPhase>builder()
                        .message("Cập nhập giai đoạn thực tập thành công")
                        .success(true)
                        .data(internshipPhaseService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable Integer id) {
        internshipPhaseService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa giai đoạn thực tập dùng thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
