package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.math.BigDecimal;

@Data
public class OutcomeDTO {
    private Long Id;
    private String outcomeDescription;
    private String outcomePlace;
    private BigDecimal outcomePrice;
    private PaymentMethod paymentMethod;
    private Long outcomeOrderId;
}
