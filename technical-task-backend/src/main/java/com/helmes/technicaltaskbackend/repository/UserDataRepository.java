package com.helmes.technicaltaskbackend.repository;

import com.helmes.technicaltaskbackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findBySessionId(String sessionId);
}
