package com.helmes.technicaltaskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SectorDTO {
    private int id;
    private String name;
    private List<SectorDTO> subsectors;
}
