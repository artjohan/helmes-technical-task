package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.entity.SectorEntity;
import com.helmes.technicaltaskbackend.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SectorServiceTest {

    @Mock
    private SectorRepository sectorRepository;

    @InjectMocks
    private SectorService sectorService;

    @Test
    void givenSectorWithSubsectors_whenGettingSectors_thenShouldReturnNestedSectorDTOList() {
        SectorEntity parentSector = new SectorEntity(1, "parentSector", null);

        SectorEntity subsector = new SectorEntity(2, "subsector", 1);

        SectorEntity subSubsector = new SectorEntity(3, "subSubsector", 2);

        List<SectorEntity> databaseResponse = List.of(parentSector, subsector, subSubsector);
        List<SectorDTO> expected = List.of(
                new SectorDTO(1, "parentSector", List.of(
                        new SectorDTO(2, "subsector", List.of(
                                new SectorDTO(3, "subSubsector", Collections.emptyList())
                        ))
                )));

        when(sectorRepository.findAll()).thenReturn(databaseResponse);

        List<SectorDTO> actual = sectorService.getSectors();

        assertEquals(expected, actual);
    }

    @Test
    void givenOnlyParentSectors_whenGettingSectors_thenShouldReturnNormalSectorDTOList() {
        SectorEntity sector1 = new SectorEntity(1, "sector1", null);

        SectorEntity sector2 = new SectorEntity(2, "sector2", null);

        SectorEntity sector3 = new SectorEntity(3, "sector3", null);

        List<SectorEntity> databaseResponse = List.of(sector1, sector2, sector3);
        List<SectorDTO> expected = List.of(
                new SectorDTO(1, "sector1", Collections.emptyList()),
                new SectorDTO(2, "sector2", Collections.emptyList()),
                new SectorDTO(3, "sector3", Collections.emptyList()));

        when(sectorRepository.findAll()).thenReturn(databaseResponse);

        List<SectorDTO> actual = sectorService.getSectors();

        assertEquals(expected, actual);
    }
}
