package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.IncomePlatform;
import org.BORDICO.Model.Enum.PaymentMethod;

@Data
public class IncomeOrderInput {
    private String clientName;
    private String shippingAddress;
    private Double orderPrice;
    private PaymentMethod paymentMethod;
    private IncomePlatform incomePlatform;
    private Long paymentId;
}
