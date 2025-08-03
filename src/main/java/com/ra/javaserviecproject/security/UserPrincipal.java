package com.ra.javaserviecproject.security;

import com.ra.javaserviecproject.model.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPrincipal implements UserDetails {
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.isStatus();
    }
}
