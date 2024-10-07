package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.CartStatus;

import java.math.BigDecimal;

@Data
public class CartInput {
    private BigDecimal cartPrice;
    private Integer cartQuantity;
    private CartStatus cartStatus;
    private Long userId;
}
