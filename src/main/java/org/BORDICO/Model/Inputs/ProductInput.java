package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInput {
    private String productName;
    private BigDecimal productPrice;
    private Double productWidth;
    private Double productHeight;
    private Double productLength;
    private Double productWeight;
}
