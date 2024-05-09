package com.helmes.technicaltaskbackend.controller;

import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping("/get-sectors")
    public List<SectorDTO> getSectors() {
        return sectorService.getSectorResponse();
    }
}
