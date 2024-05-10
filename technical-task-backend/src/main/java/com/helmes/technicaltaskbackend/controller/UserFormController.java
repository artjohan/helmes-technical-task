package com.helmes.technicaltaskbackend.controller;

import com.helmes.technicaltaskbackend.dto.UserFormDTO;
import com.helmes.technicaltaskbackend.service.UserFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserFormController {

    private final UserFormService userFormService;

    @PostMapping("/form")
    private UserFormDTO saveForm(@RequestBody UserFormDTO form) {
        return userFormService.saveForm(form);
    }
}
