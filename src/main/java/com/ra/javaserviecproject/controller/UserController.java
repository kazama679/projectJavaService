package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.UserUpdateDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.security.UserPrincipal;
import com.ra.javaserviecproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<APIResponse<List<User>>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<User>>builder()
                        .message("Lấy danh sách người dùng thành công")
                        .success(true)
                        .data(users)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<User>> getUserById(@PathVariable Integer id, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        System.out.println("Logged in user id: " + userPrincipal.getUser().getId());
        System.out.println("Requested user id: " + id);
        if(userPrincipal.getUser().getRoles().toString().equals("STUDENT")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(APIResponse.<User>builder()
                            .message("Bạn không có quyền truy cập thông tin người dùng này")
                            .success(false)
                            .data(null)
                            .timestamp(LocalDateTime.now())
                            .build());
        }else{
            return ResponseEntity.ok(
                    APIResponse.<User>builder()
                            .message("Lấy thông tin người dùng thành công")
                            .success(true)
                            .data(userService.findById(id))
                            .timestamp(LocalDateTime.now())
                            .build()
            );
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<APIResponse<User>> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(
                APIResponse.<User>builder()
                        .message("Cập nhật người dùng thành công")
                        .success(true)
                        .data(userService.update(dto, id))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .message("Xóa người dùng thành công")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}