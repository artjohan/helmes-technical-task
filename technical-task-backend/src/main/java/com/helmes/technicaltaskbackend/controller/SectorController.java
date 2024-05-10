package com.helmes.technicaltaskbackend.controller;

import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping("/sectors")
    public List<SectorDTO> getSectors() {
        return sectorService.getSectors();
    }
}
