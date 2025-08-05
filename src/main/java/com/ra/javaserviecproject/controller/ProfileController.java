package com.ra.javaserviecproject.controller;

import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @GetMapping("/me")
    public ResponseEntity<User> getProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return new ResponseEntity<>(userPrincipal.getUser(), HttpStatus.OK);
    }
}