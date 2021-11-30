package com.zipbom.zipbom.repository;

import com.zipbom.zipbom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
