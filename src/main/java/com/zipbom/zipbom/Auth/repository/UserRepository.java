package com.zipbom.zipbom.Auth.repository;

import com.zipbom.zipbom.Auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where providerId = :providerId", nativeQuery = true)
    Optional<User> findByProviderId(@Param("providerId") String providerId);

    Boolean existsByEmail(String email);
}
