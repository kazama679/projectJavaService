package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = :username")
    public List<User> findByUserName(@Param("username") String userName);
}
