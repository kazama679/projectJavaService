package com.ra.javaserviecproject.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterResponse {
    private Integer id;
    private String fullName;
    private String username;
    private String email ;
    private String phone ;
    private String role ;
}
