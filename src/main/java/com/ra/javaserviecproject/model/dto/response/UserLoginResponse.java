package com.ra.javaserviecproject.model.dto.response;

import com.ra.javaserviecproject.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserLoginResponse {
    private String userName;
    private Set<Role> roles;
    private String accessToken;
    private String refreshToken;
}
