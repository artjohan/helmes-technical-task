package com.helmes.technicaltaskbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user_sectors")
public class UserSectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int sectorId;
    private String userSessionId;

    public UserSectorEntity(int sectorId, String userSessionId) {
        this.sectorId = sectorId;
        this.userSessionId = userSessionId;
    }
}