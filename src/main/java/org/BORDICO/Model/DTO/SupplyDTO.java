package org.BORDICO.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class SupplyDTO {
    private Long Id;
    private String supplyName;
    private BigDecimal supplyPrice;
    private Integer supplyQuantity;
    private Boolean supplyIsYarn;
    private Double yarnGrams;
    private String supplyBrand;
    private Set<Long> outcomeOrdersId;
}
