package com.ra.javaserviecproject.security.jwt;

import com.ra.javaserviecproject.model.entity.User;
import com.ra.javaserviecproject.security.UserDetailService;
import com.ra.javaserviecproject.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilterChainInternal extends OncePerRequestFilter {
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token != null) {
            String username = jwtProvider.getUsernameFromToken(token);
            if (username != null) {
                User user = userService.findByUserName(username);
                if (user != null && user.isLogin() && user.isStatus() && jwtProvider.validateToken(token)) {
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }else {
            return header.substring(7);
        }
    }
}
