package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.IncomePlatform;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.math.BigDecimal;

@Data
public class IncomeInput {
    private String productReference;
    private String incomeDescription;
    private BigDecimal incomePrice;
    private IncomePlatform incomePlatform;
    private PaymentMethod paymentMethod;
    private Long IncomeOrderId;
}
