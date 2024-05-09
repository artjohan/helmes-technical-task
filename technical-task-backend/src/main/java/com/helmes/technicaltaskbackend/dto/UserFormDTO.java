package com.helmes.technicaltaskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserFormDTO {
    private String name;
    private boolean agreedToTerms;
    private String sessionId;
    private List<Integer> userSectorIds;
}
