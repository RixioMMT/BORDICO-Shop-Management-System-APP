package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.math.BigDecimal;

@Data
public class OutcomeInput {
    private String outcomeDescription;
    private String outcomePlace;
    private BigDecimal outcomePrice;
    private PaymentMethod paymentMethod;
    private Long outcomeOrderId;
}
