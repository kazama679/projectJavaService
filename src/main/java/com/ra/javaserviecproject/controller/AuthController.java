package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.dto.request.UserLoginDTO;
import com.ra.javaserviecproject.model.dto.request.UserRegisterDTO;
import com.ra.javaserviecproject.model.dto.response.APIResponse;
import com.ra.javaserviecproject.model.dto.response.UserLoginResponse;
import com.ra.javaserviecproject.model.dto.response.UserRegisterResponse;
import com.ra.javaserviecproject.security.UserPrincipal;
import com.ra.javaserviecproject.service.HeaderService;
import com.ra.javaserviecproject.service.UserService;
import com.ra.javaserviecproject.service.UserTokenRefreshService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private HeaderService headerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenRefreshService userTokenRefreshService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<UserLoginResponse>> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        UserLoginResponse userLoginResponse = userService.login(userLoginDTO, request);
        if (userLoginResponse == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(APIResponse.<UserLoginResponse>builder()
                            .success(false)
                            .message("Tên người dùng hoặc mật khẩu không đúng")
                            .data(null)
                            .timestamp(LocalDateTime.now())
                            .build());
        } else {
            return ResponseEntity.ok(APIResponse.<UserLoginResponse>builder()
                    .success(true)
                    .message("Đăng nhập thành công")
                    .data(userLoginResponse)
                    .timestamp(LocalDateTime.now())
                    .build());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<UserRegisterResponse>> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserRegisterResponse userRegisterResponse = userService.register(userRegisterDTO);
        if (userRegisterResponse == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(APIResponse.<UserRegisterResponse>builder()
                            .success(false)
                            .message("Đăng ký không thành công")
                            .data(null)
                            .timestamp(LocalDateTime.now())
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(APIResponse.<UserRegisterResponse>builder()
                            .success(true)
                            .message("Đăng ký thành công")
                            .data(userRegisterResponse)
                            .timestamp(LocalDateTime.now())
                            .build());
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<APIResponse<String>> refreshToken(@RequestParam("refreshToken") String refreshToken, HttpServletRequest request) {
        String ipAddress = headerService.getIPFromHeader(request);
        String token = userTokenRefreshService.refreshToken(ipAddress, refreshToken);
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(APIResponse.<String>builder()
                            .success(false)
                            .message("Refresh token không hợp lệ")
                            .data(null)
                            .timestamp(LocalDateTime.now())
                            .build());
        } else {
            return ResponseEntity.ok(APIResponse.<String>builder()
                    .success(true)
                    .message("Làm mới token thành công")
                    .data(token)
                    .timestamp(LocalDateTime.now())
                    .build());
        }
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<APIResponse<String>> logout(HttpServletRequest request, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal != null) {
            boolean rsLogout = userService.logout(request, userPrincipal.getUser());
            if (rsLogout) {
                return ResponseEntity.ok(APIResponse.<String>builder()
                        .success(true)
                        .message("Đăng xuất thành công")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build());
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(APIResponse.<String>builder()
                                .success(false)
                                .message("Đăng xuất không thành công")
                                .data(null)
                                .timestamp(LocalDateTime.now())
                                .build());
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(APIResponse.<String>builder()
                            .success(false)
                            .message("Xác thực không thành công")
                            .data(null)
                            .timestamp(LocalDateTime.now())
                            .build());
        }
    }
}