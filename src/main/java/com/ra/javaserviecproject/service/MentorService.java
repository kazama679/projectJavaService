package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.MentorDTO;
import com.ra.javaserviecproject.model.entity.Mentor;

import java.util.List;

public interface MentorService {
    List<Mentor> findAll();
    Mentor findById(Integer id);
    Mentor add(MentorDTO mentor);
    Mentor update(MentorDTO mentor,Integer id);
    void delete(Integer id);
}
