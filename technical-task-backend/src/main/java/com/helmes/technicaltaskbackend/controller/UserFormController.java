package com.helmes.technicaltaskbackend.controller;

import com.helmes.technicaltaskbackend.dto.UserFormDTO;
import com.helmes.technicaltaskbackend.service.UserFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserFormController {

    @Autowired
    private UserFormService userFormService;

    @PostMapping("/send-form")
    private UserFormDTO saveForm(@RequestBody UserFormDTO form) {
        return userFormService.saveForm(form);
    }
}
