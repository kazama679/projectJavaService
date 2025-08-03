package com.ra.javaserviecproject.service.impl;

import com.ra.javaserviecproject.model.dto.request.MentorDTO;
import com.ra.javaserviecproject.model.entity.Mentor;
import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.repository.MentorRepository;
import com.ra.javaserviecproject.repository.UserRepository;
import com.ra.javaserviecproject.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {
    @Autowired
    public MentorRepository mentorRepository;
    @Autowired
    public UserRepository userRepository;

    @Override
    public Mentor add(MentorDTO mentor) {
        User user = userRepository.findById(mentor.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: " + mentor.getId()));
        Mentor newMentor = Mentor.builder()
                .department(mentor.getDepartment())
                .academicRank(mentor.getAcademicRank())
                .user(user)
                .build();
        return mentorRepository.save(newMentor);
    }

    @Override
    public List<Mentor> findAll() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor findById(Integer id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên với id: " + id));
    }

    @Override
    public Mentor update(MentorDTO mentor, Integer id) {
        Mentor existingMentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên với id: " + id));
        existingMentor.setDepartment(mentor.getDepartment());
        existingMentor.setAcademicRank(mentor.getAcademicRank());
        return mentorRepository.save(existingMentor);
    }

    @Override
    public void delete(Integer id) {
        if (!mentorRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy giáo viên với id: " + id);
        }
        mentorRepository.delete(findById(id));
    }
}
