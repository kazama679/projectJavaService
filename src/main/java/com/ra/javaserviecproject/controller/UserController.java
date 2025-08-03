//package com.ra.projectJavaService.controller;
//
//import com.ra.javaserviecproject.model.dto.response.APIResponse;
//import com.ra.javaserviecproject.model.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public ResponseEntity<APIResponse<List<User>>> getAllUsers(@RequestParam(required = false) StatusRole role) {
//        List<User> users = (role == null) ? userService.findAll() : userService.findByRole(role);
//        return ResponseEntity.ok(
//                APIResponse.<List<User>>builder()
//                        .message("Lấy danh sách người dùng thành công")
//                        .success(true)
//                        .data(users)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<APIResponse<User>> getUserById(@PathVariable Integer id) {
//        return ResponseEntity.ok(
//                APIResponse.<User>builder()
//                        .message("Lấy thông tin người dùng thành công")
//                        .success(true)
//                        .data(userService.findById(id))
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<APIResponse<User>> updateUser(@PathVariable Integer id, @RequestBody UserDTO dto) {
//        return ResponseEntity.ok(
//                APIResponse.<User>builder()
//                        .message("Cập nhật người dùng thành công")
//                        .success(true)
//                        .data(userService.update(id, dto))
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<APIResponse<Void>> deleteUser(@PathVariable Integer id) {
//        userService.delete(id);
//        return ResponseEntity.ok(
//                APIResponse.<Void>builder()
//                        .message("Xóa người dùng thành công")
//                        .success(true)
//                        .timestamp(LocalDateTime.now())
//                        .build()
//        );
//    }
//
//}
