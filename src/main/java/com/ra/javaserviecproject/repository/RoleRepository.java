package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRoleName(String roleName);
}
