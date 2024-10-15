package org.BORDICO.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductDTO {
    private Long Id;
    private String productName;
    private BigDecimal productPrice;
    private Double productWidth;
    private Double productHeight;
    private Double productLength;
    private Double productWeight;
    private Integer stockQuantity;
    private BigDecimal currentInvestment;
    private Integer unitsSold;
    private BigDecimal overallEarnings;
    private Set<Long> categoriesId;
    private Long patternId;
    private Set<Long> productsInventoryId;
    private Set<Long> reviewsId;
}
