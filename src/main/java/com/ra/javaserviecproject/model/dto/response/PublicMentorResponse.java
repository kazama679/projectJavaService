package com.ra.javaserviecproject.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicMentorResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String department;
    private String academicRank;
}
