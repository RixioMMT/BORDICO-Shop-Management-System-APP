package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemInput {
    private String itemName;
    private String itemColorType;
    private Integer itemQuantity;
    private Long cartId;
}
