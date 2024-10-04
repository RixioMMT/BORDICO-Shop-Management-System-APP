package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInput {
    private String productName;
    private BigDecimal productPrice;
    private double productWidth;
    private double productHeight;
    private double productLength;
    private double productWeight;
}
