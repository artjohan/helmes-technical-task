package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.dto.UserFormDTO;
import com.helmes.technicaltaskbackend.entity.UserEntity;
import com.helmes.technicaltaskbackend.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDataRepository userDataRepository;

    UserEntity createNewUser(UserFormDTO form) {
       return userDataRepository.save(new UserEntity(form.getName(), form.isAgreedToTerms(), form.getSessionId()));
    }

    UserEntity updateExistingUser(UserFormDTO form, UserEntity existingUser) {
        existingUser.setName(form.getName());
        existingUser.setAgreedToTerms(form.isAgreedToTerms());

        return userDataRepository.save(existingUser);
    }
}
