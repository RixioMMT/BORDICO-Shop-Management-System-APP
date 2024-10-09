package org.BORDICO.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplyInventoryDTO {
    private Long id;
    private String supplyName;
    private BigDecimal supplyPrice;
    private Boolean supplyIsYarn;
    private Double yarnGrams;
    private String supplyBrand;
    private Long outcomeOrderId;
}
