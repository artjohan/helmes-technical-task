package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.dto.UserFormDTO;
import com.helmes.technicaltaskbackend.entity.UserEntity;
import com.helmes.technicaltaskbackend.entity.UserSectorEntity;
import com.helmes.technicaltaskbackend.repository.UserDataRepository;
import com.helmes.technicaltaskbackend.repository.UserSectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFormService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserSectorRepository userSectorRepository;

    @Transactional
    public UserFormDTO saveForm(UserFormDTO form) {
        saveUserData(form);
        saveUserSectors(form);

        return getUserDataAndSectors(form.getSessionId());
    }

    private void saveUserData(UserFormDTO form) {
        UserEntity existingUser = userDataRepository.findBySessionId(form.getSessionId());

        if (existingUser != null) {
            existingUser.setName(form.getName());
            existingUser.setAgreedToTerms(form.isAgreedToTerms());

            userDataRepository.save(existingUser);
        } else {
            UserEntity newUser = new UserEntity();
            newUser.setName(form.getName());
            newUser.setAgreedToTerms(form.isAgreedToTerms());
            newUser.setSessionId(form.getSessionId());

            userDataRepository.save(newUser);
        }
    }

    private void saveUserSectors(UserFormDTO form) {
        String sessionId = form.getSessionId();

        userSectorRepository.deleteByUserSessionId(sessionId);

        form.getUserSectorIds().forEach(sectorId -> {
            UserSectorEntity userSector = new UserSectorEntity();

            userSector.setSectorId(sectorId);
            userSector.setUserSessionId(sessionId);

            userSectorRepository.save(userSector);
        });
    }

    private UserFormDTO getUserDataAndSectors(String sessionId) {
        UserEntity userData = userDataRepository.findBySessionId(sessionId);

        List<Integer> userSectorIds = userSectorRepository.findDistinctSectorIdByUserSessionId(sessionId);

        return new UserFormDTO(userData.getName(), userData.getAgreedToTerms(), userData.getSessionId(), userSectorIds);
    }
}
