package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.dto.UserFormDTO;
import com.helmes.technicaltaskbackend.entity.UserEntity;
import com.helmes.technicaltaskbackend.entity.UserSectorEntity;
import com.helmes.technicaltaskbackend.repository.UserDataRepository;
import com.helmes.technicaltaskbackend.repository.UserSectorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFormService {

    private final UserDataRepository userDataRepository;

    private final UserSectorRepository userSectorRepository;

    private final UserService userService;

    @Transactional
    public UserFormDTO saveForm(UserFormDTO form) {
        saveUserData(form);
        saveUserSectors(form);

        return getUserFormBySessionId(form.getSessionId());
    }

    private void saveUserData(UserFormDTO form) {
        UserEntity existingUser = userDataRepository.findBySessionId(form.getSessionId());

        if (existingUser != null) {
            userService.updateExistingUser(form, existingUser);
        } else {
            userService.createNewUser(form);
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

    private UserFormDTO getUserFormBySessionId(String sessionId) {
        UserEntity userData = userDataRepository.findBySessionId(sessionId);

        List<Integer> userSectorIds = userSectorRepository.findDistinctByUserSessionId(sessionId).stream().map(UserSectorEntity::getSectorId).toList();

        return new UserFormDTO(userData.getName(), userData.getAgreedToTerms(), userData.getSessionId(), userSectorIds);
    }

}
