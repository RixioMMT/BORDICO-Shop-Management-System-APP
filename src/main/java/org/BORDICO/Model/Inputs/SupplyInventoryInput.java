package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplyInventoryInput {
    private String supplyName;
    private BigDecimal supplyPrice;
    private Boolean supplyIsYarn;
    private Double yarnGrams;
    private String supplyBrand;
    private Long outcomeOrderId;
}
