package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.MentorDTO;
import com.ra.javaserviecproject.model.dto.request.MentorUpdateDTO;
import com.ra.javaserviecproject.model.dto.response.PublicMentorResponse;
import com.ra.javaserviecproject.model.entity.Mentor;
import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.repository.MentorRepository;
import com.ra.javaserviecproject.repository.UserRepository;
import com.ra.javaserviecproject.security.UserPrincipal;
import com.ra.javaserviecproject.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements MentorService {
    @Autowired
    public MentorRepository mentorRepository;
    @Autowired
    public UserRepository userRepository;

    @Override
    public Mentor add(UserPrincipal userPrincipal, MentorDTO mentor) {
        User user = userRepository.findById(mentor.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: " + mentor.getId()));
        boolean isMentor = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equalsIgnoreCase("MENTOR"));
        if (!isMentor) {
            throw new RuntimeException("User này không có quyền MENTOR");
        }
        if (mentorRepository.existsById(mentor.getId())) {
            throw new RuntimeException("User này đã là giảng viên rồi");
        }
        Mentor newMentor = Mentor.builder()
                .user(user)
                .academicRank(mentor.getAcademicRank())
                .department(mentor.getDepartment())
                .build();
        return mentorRepository.save(newMentor);
    }

    @Override
    public List<?> findAll(UserPrincipal userPrincipal) {
        boolean isAdmin = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("ADMIN"));
        if (isAdmin) {
            return mentorRepository.findAll();
        }

        boolean isStudent = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("STUDENT"));
        if (isStudent) {
            List<Mentor> mentors = mentorRepository.findAll();
            return mentors.stream()
                    .map(mentor -> PublicMentorResponse.builder()
                            .fullName(mentor.getUser().getFullName())
                            .email(mentor.getUser().getEmail())
                            .phoneNumber(mentor.getUser().getPhoneNumber())
                            .department(mentor.getDepartment())
                            .academicRank(mentor.getAcademicRank())
                            .build())
                    .collect(Collectors.toList());
        }
        throw new RuntimeException("Bạn không có quyền xem danh sách giảng viên");
    }

    @Override
    public Object findById(UserPrincipal userPrincipal, Integer id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với id: " + id));
        boolean isAdmin = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("ADMIN"));
        if (isAdmin) {
            return mentor;
        }
        boolean isStudent = userPrincipal.getUser().getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("STUDENT"));
        if (isStudent) {
            return PublicMentorResponse.builder()
                    .fullName(mentor.getUser().getFullName())
                    .email(mentor.getUser().getEmail())
                    .phoneNumber(mentor.getUser().getPhoneNumber())
                    .department(mentor.getDepartment())
                    .academicRank(mentor.getAcademicRank())
                    .build();
        }
        boolean isMentor = userPrincipal.getUser().getRoles().stream()
                .anyMatch(auth -> auth.getRoleName().equals("MENTOR"));
        if (isMentor) {
            if (!userPrincipal.getUser().getId().equals(id)) {
                throw new RuntimeException("Bạn không có quyền xem thông tin giảng viên khác");
            }
            return mentor;
        }
        throw new RuntimeException("Bạn không có quyền xem chi tiết giảng viên");
    }

    @Override
    public Mentor update(UserPrincipal userPrincipal, MentorUpdateDTO mentor, Integer id) {
        boolean isAdmin = userPrincipal.getUser().getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("ADMIN"));
        boolean isMentor = userPrincipal.getUser().getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("MENTOR"));
        if (isAdmin) {
            // Cho phép admin cập nhật bất kỳ sinh viên nào
        } else if (isMentor) {
            Mentor currentMentor = mentorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với id: " + id));
            if (!currentMentor.getUser().getId().equals(userPrincipal.getUser().getId())) {
                throw new RuntimeException("Bạn không có quyền chỉnh sửa thông tin giảng viên khác");
            }
        } else {
            throw new RuntimeException("Bạn không có quyền chỉnh sửa thông tin giảng viên");
        }
        Mentor existingMentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với id: " + id));
        existingMentor.setDepartment(mentor.getDepartment());
        existingMentor.setAcademicRank(mentor.getAcademicRank());
        return mentorRepository.save(existingMentor);
    }
}