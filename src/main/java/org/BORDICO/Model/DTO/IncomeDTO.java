package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.IncomePlatform;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.math.BigDecimal;

@Data
public class IncomeDTO {
    private Long id;
    private String incomeDescription;
    private BigDecimal incomePrice;
    private IncomePlatform incomePlatform;
    private PaymentMethod paymentMethod;
    private Long IncomeOrderId;
}
