package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = :username")
    public List<User> findByUserName(@Param("username") String userName);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByPhoneNumber(String phoneNumber);
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.phoneNumber = :phoneNumber AND u.id != :id")
    boolean existsByPhoneAndIdNot(@Param("phoneNumber") String phoneNumber, @Param("id") Integer id);
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email AND u.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Integer id);
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username AND u.id != :id")
    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("id") Integer id);
}