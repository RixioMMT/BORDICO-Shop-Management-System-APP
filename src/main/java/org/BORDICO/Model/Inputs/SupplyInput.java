package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplyInput {
    private String supplyName;
    private BigDecimal supplyPrice;
    private Integer supplyQuantity;
    private Boolean supplyIsYarn;
    private Double yarnGrams;
    private String supplyBrand;
}
