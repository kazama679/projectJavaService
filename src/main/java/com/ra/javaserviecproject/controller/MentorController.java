package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.MentorDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.Mentor;
import com.ra.javaserviecproject.service.MentorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping
    public ResponseEntity<APIResponse<Mentor>> add(@Valid @RequestBody MentorDTO mentor) {
        Mentor newMentor = mentorService.add(mentor);
        return ResponseEntity.status(201).body(
                APIResponse.<Mentor>builder().message("Đăng ký thành công").data(newMentor).success(true).timestamp(LocalDateTime.now()).build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Mentor>>> getAll() {
        return ResponseEntity.ok(
                APIResponse.<List<Mentor>>builder()
                        .message("Lấy danh giáo viên dùng thành công")
                        .success(true)
                        .data(mentorService.findAll())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Mentor>> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<Mentor>builder()
                        .message("Lấy thông tin giáo viên thành công")
                        .success(true)
                        .data(mentorService.findById(id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<Mentor>> updateUser(@PathVariable Integer id, @RequestBody MentorDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<Mentor>builder()
                        .message("Cập giáo viên dùng thành công")
                        .success(true)
                        .data(mentorService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
        mentorService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa giáo viên dùng thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
