package com.helmes.technicaltaskbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Boolean agreedToTerms;
    private String sessionId;

    public UserEntity(String name, Boolean agreedToTerms, String sessionId) {
        this.name = name;
        this.agreedToTerms = agreedToTerms;
        this.sessionId = sessionId;
    }
}
