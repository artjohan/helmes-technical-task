package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.entity.SectorEntity;
import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<SectorDTO> getSectorResponse() {
        List<SectorEntity> allSectors = sectorRepository.findAll();
        List<SectorEntity> mainSectors = allSectors.stream().filter(sector -> sector.getParentSectorId() == null).toList();
        List<SectorDTO> sectorsResponse = new LinkedList<>();

        mainSectors.forEach(sector -> sectorsResponse.add(getSectorDataRecursively(sector, allSectors)));

        return sectorsResponse;
    }

    private SectorDTO getSectorDataRecursively(SectorEntity sector, List<SectorEntity> allSectors) {
        List<SectorEntity> subsectors = getSubsectors(sector.getId(), allSectors);
        List<SectorDTO> subsectorsResponse = new LinkedList<>();

        subsectors.forEach(subsector -> subsectorsResponse.add(getSectorDataRecursively(subsector, allSectors)));

        return new SectorDTO(sector.getId(), sector.getName(), subsectorsResponse);
    }


    private List<SectorEntity> getSubsectors(int sectorId, List<SectorEntity> allSectors) {
        return allSectors.stream().filter(sector -> Objects.equals(sector.getParentSectorId(), sectorId)).toList();
    }

}
