package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.math.BigDecimal;

@Data
public class OutcomeOrderInput {
    private String orderPlace;
    private BigDecimal orderPrice;
    private PaymentMethod paymentMethod;
    private Long outcomeId;
}
