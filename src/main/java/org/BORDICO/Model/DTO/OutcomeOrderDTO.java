package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class OutcomeOrderDTO {
    private Long id;
    private String orderPlace;
    private BigDecimal orderPrice;
    private PaymentMethod paymentMethod;
    private Long outcomeId;
    private Set<Long> suppliesInventoryId;
}
