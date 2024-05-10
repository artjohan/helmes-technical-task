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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class UserFormServiceTest {

    @Mock
    UserSectorRepository userSectorRepository;

    @Mock
    UserDataRepository userDataRepository;

    @Mock
    UserService userService;

    @InjectMocks
    UserFormService userFormService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenValidUserFormObject_whenSubmittingForm_thenShouldReturnObjectWithSameValues() {
        String sessionId = "session-id";
        String name = "Art";

        UserFormDTO userForm = new UserFormDTO(name, true, sessionId, List.of(4, 5, 6));

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setName(name);
        user.setSessionId(sessionId);
        user.setAgreedToTerms(true);

        UserSectorEntity sector1 = new UserSectorEntity();
        UserSectorEntity sector2 = new UserSectorEntity();
        UserSectorEntity sector3 = new UserSectorEntity();

        sector1.setId(1);
        sector2.setId(2);
        sector3.setId(3);

        sector1.setSectorId(4);
        sector2.setSectorId(5);
        sector3.setSectorId(6);

        sector1.setUserSessionId(sessionId);
        sector2.setUserSessionId(sessionId);
        sector3.setUserSessionId(sessionId);

        when(userDataRepository.findBySessionId(sessionId)).thenReturn(user);
        when(userSectorRepository.findDistinctByUserSessionId(sessionId)).thenReturn(List.of(sector1, sector2, sector3));

        UserFormDTO expected = new UserFormDTO(name, true, sessionId, List.of(4, 5, 6));

        UserFormDTO actual = userFormService.saveForm(userForm);

        assertEquals(expected, actual);
    }
}
