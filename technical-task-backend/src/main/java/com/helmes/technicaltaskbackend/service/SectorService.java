package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.entity.SectorEntity;
import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    public List<SectorDTO> getSectors() {
        List<SectorEntity> allSectors = sectorRepository.findAll();

        return allSectors.stream()
                .filter(sector -> sector.getParentSectorId() == null)
                .map(parentSector -> attachSubsectors(parentSector, allSectors))
                .toList();
    }

    private SectorDTO attachSubsectors(SectorEntity sector, List<SectorEntity> allSectors) {
        List<SectorEntity> directSubsectors = getSubsectors(sector.getId(), allSectors);
        List<SectorDTO> allSubsectors = new ArrayList<>();

        directSubsectors.forEach(subsector -> allSubsectors.add(attachSubsectors(subsector, allSectors)));

        return new SectorDTO(sector.getId(), sector.getName(), allSubsectors);
    }

    private List<SectorEntity> getSubsectors(int sectorId, List<SectorEntity> allSectors) {
        return allSectors.stream().filter(sector -> Objects.equals(sector.getParentSectorId(), sectorId)).toList();
    }

}
