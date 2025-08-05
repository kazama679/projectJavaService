package com.ra.javaserviecproject.service.authJwt;

import com.ra.javaserviecproject.model.entity.UserTokenRefresh;
import com.ra.javaserviecproject.repository.UserTokenRefreshRepository;
import com.ra.javaserviecproject.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenRefreshService {
    @Autowired
    private UserTokenRefreshRepository userTokenRefreshRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public void add(UserTokenRefresh userTokenRefresh) {
        userTokenRefreshRepository.save(userTokenRefresh);
    }

    public String refreshToken(String ipAddress , String refreshToken ) {
        boolean checkTokenRefreshValid = userTokenRefreshRepository.checkUserTokenRefresh(ipAddress, refreshToken) > 0 ;
        if(checkTokenRefreshValid) {
            String username = jwtProvider.getUsernameFromToken(refreshToken);
            return jwtProvider.generateToken(username);
        }else {
            return null;
        }
    }

    public UserTokenRefresh checkUserTokenRefreshLogin(Integer userId , String ipAddress) {
        return userTokenRefreshRepository.checkUserTokenRefreshLogin(userId,ipAddress).stream().findFirst().orElse(null) ;
    }

    public boolean deleteUserTokenRefresh(UserTokenRefresh userTokenRefresh) {
        try {
            userTokenRefreshRepository.delete(userTokenRefresh);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
