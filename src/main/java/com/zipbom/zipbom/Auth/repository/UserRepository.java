package com.zipbom.zipbom.Auth.repository;

import com.zipbom.zipbom.Auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
