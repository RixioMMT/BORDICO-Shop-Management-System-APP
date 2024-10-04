package org.BORDICO.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductDTO {
    private Long Id;
    private String productName;
    private BigDecimal productPrice;
    private double productWidth;
    private double productHeight;
    private double productLength;
    private double productWeight;
    private Set<String> categoryNames;
    private String patternName;
    private Set<String> reviews;
}
