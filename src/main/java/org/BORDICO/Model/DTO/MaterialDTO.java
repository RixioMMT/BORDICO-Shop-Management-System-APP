package org.BORDICO.Model.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class MaterialDTO {
    private Long id;
    private String supplyName;
    private Boolean supplyIsYarn;
    private Double yarnGrams;
    private Set<Long> patternsId;
}
