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

    void createNewUser(UserFormDTO form) {
        UserEntity newUser = new UserEntity();
        newUser.setName(form.getName());
        newUser.setAgreedToTerms(form.isAgreedToTerms());
        newUser.setSessionId(form.getSessionId());

        userDataRepository.save(newUser);
    }

    void updateExistingUser(UserFormDTO form, UserEntity existingUser) {
        existingUser.setName(form.getName());
        existingUser.setAgreedToTerms(form.isAgreedToTerms());

        userDataRepository.save(existingUser);
    }
}
