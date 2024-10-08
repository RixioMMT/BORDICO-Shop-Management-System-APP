package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.PaymentMethod;
import org.BORDICO.Model.Enum.PaymentStatus;

@Data
public class PaymentInput {
    private String clientName;
    private String bank;
    private String confirmationNumber;
    private Double paymentPrice;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Long cartId;
}
