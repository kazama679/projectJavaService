package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.UserLoginDTO;
import com.ra.javaserviecproject.model.dto.request.UserRegisterDTO;
import com.ra.javaserviecproject.model.dto.request.UserUpdateDTO;
import com.ra.javaserviecproject.model.dto.response.UserLoginResponse;
import com.ra.javaserviecproject.model.dto.response.UserRegisterResponse;
import com.ra.javaserviecproject.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    User findByUserName(String userName);
    UserLoginResponse login(UserLoginDTO userLoginDTO, HttpServletRequest request);
    UserRegisterResponse register(UserRegisterDTO userRegisterDTO);
    boolean logout(HttpServletRequest request ,User user);
    List<User> findAll();
    User findById(Integer id);
    User update(UserUpdateDTO user, Integer id);
    void delete(Integer id);
}
