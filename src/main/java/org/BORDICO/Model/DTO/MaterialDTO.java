package org.BORDICO.Model.DTO;

import lombok.Data;

@Data
public class MaterialDTO {
    private Long id;
    private String supplyName;
    private Boolean supplyIsYarn;
    private Double yarnGrams;
}
