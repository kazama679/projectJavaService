package com.ra.javaserviecproject.repository;

import com.ra.javaserviecproject.model.entity.UserTokenRefresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTokenRefreshRepository extends JpaRepository<UserTokenRefresh, Integer> {
    @Query("select count(u) from UserTokenRefresh u where  u.ipAddress = :ipAddress and u.refreshToken = :refreshToken")
    Integer checkUserTokenRefresh( @Param("ipAddress") String ipAddress , @Param("refreshToken") String refreshToken);

    @Query("select u from UserTokenRefresh u where u.user.id = :userId and u.ipAddress = :ipAddress")
    List<UserTokenRefresh> checkUserTokenRefreshLogin(@Param("userId") Integer userId , @Param("ipAddress") String ipAddress);
}
