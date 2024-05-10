package com.helmes.technicaltaskbackend.service;

import com.helmes.technicaltaskbackend.dto.SectorDTO;
import com.helmes.technicaltaskbackend.entity.SectorEntity;
import com.helmes.technicaltaskbackend.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class SectorServiceTest {

    @Mock
    SectorRepository sectorRepository;

    @InjectMocks
    SectorService sectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenSectorWithSubsectors_whenGettingSectors_thenShouldReturnNestedSectorDTOList() {
        SectorEntity parentSector = new SectorEntity();
        parentSector.setId(1);
        parentSector.setName("parentSector");
        parentSector.setParentSectorId(null);

        SectorEntity subsector = new SectorEntity();
        subsector.setId(2);
        subsector.setName("subsector");
        subsector.setParentSectorId(1);

        SectorEntity subSubsector = new SectorEntity();
        subSubsector.setId(3);
        subSubsector.setName("subSubsector");
        subSubsector.setParentSectorId(2);

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
        SectorEntity sector1 = new SectorEntity();
        sector1.setId(1);
        sector1.setName("sector1");
        sector1.setParentSectorId(null);

        SectorEntity sector2 = new SectorEntity();
        sector2.setId(2);
        sector2.setName("sector2");
        sector2.setParentSectorId(null);

        SectorEntity sector3 = new SectorEntity();
        sector3.setId(3);
        sector3.setName("sector3");
        sector3.setParentSectorId(null);

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
