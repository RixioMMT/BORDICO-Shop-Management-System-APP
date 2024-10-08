package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.CartStatus;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartDTO {
    private Long id;
    private BigDecimal cartPrice;
    private Integer cartQuantity;
    private CartStatus cartStatus;
    private Long userId;
    private Set<Long> cartItemsId;
    private Long paymentId;
}
