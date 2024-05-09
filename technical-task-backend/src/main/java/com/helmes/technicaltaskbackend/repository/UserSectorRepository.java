package com.helmes.technicaltaskbackend.repository;

import com.helmes.technicaltaskbackend.entity.UserSectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserSectorRepository extends JpaRepository<UserSectorEntity, Integer> {
    void deleteByUserSessionId(String sessionId);

    @Query("SELECT DISTINCT us.sectorId FROM UserSectorEntity us WHERE us.userSessionId = :sessionId")
    List<Integer> findDistinctSectorIdByUserSessionId(@Param("sessionId") String sessionId);
}
