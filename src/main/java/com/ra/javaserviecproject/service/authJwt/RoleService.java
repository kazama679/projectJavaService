package com.ra.javaserviecproject.service.authJwt;

import com.ra.javaserviecproject.model.entity.Role;
import com.ra.javaserviecproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
