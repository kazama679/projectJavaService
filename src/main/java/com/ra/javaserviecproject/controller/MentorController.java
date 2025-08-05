package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.MentorDTO;
import com.ra.javaserviecproject.model.dto.request.MentorUpdateDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.Mentor;
import com.ra.javaserviecproject.security.UserPrincipal;
import com.ra.javaserviecproject.service.MentorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @PostMapping
    public ResponseEntity<APIResponse<Mentor>> add(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid @RequestBody MentorDTO mentor) {
        Mentor newMentor = mentorService.add(userPrincipal, mentor);
        return ResponseEntity.status(201).body(
                APIResponse.<Mentor>builder().message("Đăng ký thành công").data(newMentor).success(true).timestamp(LocalDateTime.now()).build());
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<?>>> getAll(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(
                APIResponse.<List<?>>builder()
                        .message("Lấy danh sách giảng viên thành công")
                        .success(true)
                        .data(mentorService.findAll(userPrincipal))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Object>> getMentorById(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                             @PathVariable Integer id) {
        return ResponseEntity.ok(
                APIResponse.<Object>builder()
                        .success(true)
                        .message("Lấy thông tin giảng viên thành công")
                        .data(mentorService.findById(userPrincipal, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<Mentor>> updateUser(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Integer id, @Valid @RequestBody MentorUpdateDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<Mentor>builder()
                        .message("Cập giáo viên thành công")
                        .success(true)
                        .data(mentorService.update(userPrincipal, dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
