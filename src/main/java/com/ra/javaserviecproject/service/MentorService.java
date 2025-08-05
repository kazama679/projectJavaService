package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.MentorDTO;
import com.ra.javaserviecproject.model.dto.request.MentorUpdateDTO;
import com.ra.javaserviecproject.model.entity.Mentor;
import com.ra.javaserviecproject.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface MentorService {
    List<?> findAll(@AuthenticationPrincipal UserPrincipal userPrincipal);
    Object findById(@AuthenticationPrincipal UserPrincipal userPrincipal, Integer id);
    Mentor add(@AuthenticationPrincipal UserPrincipal userPrincipal, MentorDTO mentor);
    Mentor update(@AuthenticationPrincipal UserPrincipal userPrincipal, MentorUpdateDTO mentor, Integer id);
}
