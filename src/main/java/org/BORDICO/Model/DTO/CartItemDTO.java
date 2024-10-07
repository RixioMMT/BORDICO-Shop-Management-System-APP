package org.BORDICO.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long Id;
    private String itemName;
    private String itemColorType;
    private BigDecimal cartItemPrice;
    private Integer itemQuantity;
    private Long cartId;
}
