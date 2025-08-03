package com.ra.javaserviecproject.service;

import com.ra.javaserviecproject.model.dto.request.UserLoginDTO;
import com.ra.javaserviecproject.model.dto.request.UserRegisterDTO;
import com.ra.javaserviecproject.model.dto.response.UserLoginResponse;
import com.ra.javaserviecproject.model.dto.response.UserRegisterResponse;
import com.ra.javaserviecproject.model.entity.Role;
import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.model.entity.UserTokenRefresh;
import com.ra.javaserviecproject.repository.UserRepository;
import com.ra.javaserviecproject.security.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private HeaderService headerService;

    @Autowired
    private UserTokenRefreshService userTokenRefreshService;

    @Autowired
    private RoleService roleService;

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).stream().findFirst().orElse(null);
    }

    public UserLoginResponse login(UserLoginDTO userLoginDTO, HttpServletRequest request) {
        User user = findByUserName(userLoginDTO.getUsername());
        if(user == null) {
            return null;
        }else {
            if(passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                String ipAddress = headerService.getIPFromHeader(request);
                UserTokenRefresh oldUserTokenRefresh  = userTokenRefreshService.checkUserTokenRefreshLogin(user.getId(), ipAddress);

                user.setLogin(true);
                userRepository.save(user);
                if(oldUserTokenRefresh == null) {
                    String tokenRefresh = jwtProvider.generateRefreshToken(user.getUsername());
                    UserTokenRefresh userTokenRefresh = UserTokenRefresh
                            .builder()
                            .refreshToken(tokenRefresh)
                            .user(user)
                            .ipAddress(headerService.getIPFromHeader(request))
                            .build();
                    userTokenRefreshService.add(userTokenRefresh);

                    return UserLoginResponse
                            .builder()
                            .accessToken(jwtProvider.generateToken(user.getUsername()))
                            .refreshToken(tokenRefresh)
                            .userName(user.getUsername())
                            .roles(user.getRoles())
                            .build();
                }

                return UserLoginResponse
                        .builder()
                        .accessToken(jwtProvider.generateToken(user.getUsername()))
                        .refreshToken(oldUserTokenRefresh.getRefreshToken())
                        .userName(user.getUsername())
                        .roles(user.getRoles())
                        .build();
            }else {
                return null;
            }
        }
    }

    public UserRegisterResponse register(UserRegisterDTO userRegisterDTO) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName("STUDENT"));
        User user = User
                .builder()
                .username(userRegisterDTO.getUsername())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .email(userRegisterDTO.getEmail())
                .phone(userRegisterDTO.getPhone())
                .fullName(userRegisterDTO.getFullName())
                .isLogin(false)
                .status(true)
                .roles(roles)
                .build();
        User newUser = userRepository.save(user);
        return UserRegisterResponse
                .builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .phone(newUser.getPhone())
                .fullName(newUser.getFullName())
                .role("STUDENT")
                .build();
    }

    public boolean logout(HttpServletRequest request ,User user) {
       try {
           user.setLogin(false);
           userRepository.save(user);
           String ipAddress = headerService.getIPFromHeader(request);
           UserTokenRefresh userTokenRefresh = userTokenRefreshService.checkUserTokenRefreshLogin(user.getId(), ipAddress);
           return userTokenRefreshService.deleteUserTokenRefresh(userTokenRefresh);
       } catch (Exception e) {
           return false;
       }
    }
}
