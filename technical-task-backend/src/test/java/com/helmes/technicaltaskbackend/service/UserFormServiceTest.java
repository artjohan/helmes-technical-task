package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.dto.UserFormDTO;
import com.helmes.technicaltaskbackend.entity.SectorEntity;
import com.helmes.technicaltaskbackend.entity.UserEntity;
import com.helmes.technicaltaskbackend.entity.UserSectorEntity;
import com.helmes.technicaltaskbackend.repository.SectorRepository;
import com.helmes.technicaltaskbackend.repository.UserDataRepository;
import com.helmes.technicaltaskbackend.repository.UserSectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserFormServiceTest {

    @Mock
    private UserSectorRepository userSectorRepository;

    @Mock
    private UserDataRepository userDataRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFormService userFormService;

    @Test
    void givenExistingSessionId_whenPostingUserData_thenShouldUpdateExistingUser() {
        UserSectorEntity sector1 = new UserSectorEntity(1, "session-id");
        UserSectorEntity sector2 = new UserSectorEntity(2, "session-id");

        UserEntity existingUser = new UserEntity("Art", true, "session-id");
        UserFormDTO form = new UserFormDTO("Part", false, "session-id", List.of(1, 2));
        UserEntity updatedExistingUser = new UserEntity("Part", false, "session-id");

        when(userDataRepository.findBySessionId("session-id")).thenReturn(existingUser);
        when(userService.updateExistingUser(form, existingUser)).thenReturn(updatedExistingUser);
        when(userSectorRepository.findDistinctByUserSessionId("session-id")).thenReturn(List.of(sector1, sector2));

        UserFormDTO actual = userFormService.saveForm(form);

        verify(userSectorRepository).save(sector1);
        verify(userSectorRepository).save(sector2);

        assertEquals(actual, form);
    }

    @Test
    void givenNewSessionId_whenPostingUserData_thenShouldCreateNewUser() {
        UserSectorEntity sector1 = new UserSectorEntity(1, "session-id");
        UserSectorEntity sector2 = new UserSectorEntity(2, "session-id");

        UserFormDTO form = new UserFormDTO("Art", false, "session-id", List.of(1, 2));
        UserEntity newUser = new UserEntity("Art", false, "session-id");

        when(userDataRepository.findBySessionId("session-id")).thenReturn(null);
        when(userService.createNewUser(form)).thenReturn(newUser);
        when(userSectorRepository.findDistinctByUserSessionId("session-id")).thenReturn(List.of(sector1, sector2));

        UserFormDTO actual = userFormService.saveForm(form);

        verify(userSectorRepository).save(sector1);
        verify(userSectorRepository).save(sector2);

        assertEquals(actual, form);
    }
}
