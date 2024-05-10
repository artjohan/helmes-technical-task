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
        UserEntity savedUser = saveUserData(form);
        List<UserSectorEntity> savedUserSectors = saveUserSectors(form);

        return new UserFormDTO(
                savedUser.getName(),
                savedUser.getAgreedToTerms(), savedUser.getSessionId(),
                savedUserSectors.stream().map(UserSectorEntity::getSectorId).toList());
    }

    private UserEntity saveUserData(UserFormDTO form) {
        UserEntity existingUser = userDataRepository.findBySessionId(form.getSessionId());

        if (existingUser != null) {
            return userService.updateExistingUser(form, existingUser);
        } else {
            return userService.createNewUser(form);
        }
    }

    private List<UserSectorEntity> saveUserSectors(UserFormDTO form) {
        String sessionId = form.getSessionId();

        userSectorRepository.deleteByUserSessionId(sessionId);

        form.getUserSectorIds().forEach(sectorId -> userSectorRepository.save(new UserSectorEntity(sectorId, sessionId)));

        return userSectorRepository.findDistinctByUserSessionId(form.getSessionId());
    }

}
