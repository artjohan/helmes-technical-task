package com.helmes.technicaltaskbackend.repository;

import com.helmes.technicaltaskbackend.entity.UserSectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserSectorRepository extends JpaRepository<UserSectorEntity, Integer> {
    void deleteByUserSessionId(String sessionId);

    List <UserSectorEntity> findDistinctByUserSessionId(String userSessionId);
}
