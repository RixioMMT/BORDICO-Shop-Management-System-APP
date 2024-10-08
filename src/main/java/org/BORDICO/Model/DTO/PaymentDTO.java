package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.PaymentMethod;
import org.BORDICO.Model.Enum.PaymentStatus;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    private String clientName;
    private String bank;
    private String confirmationNumber;
    private Double paymentPrice;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime confirmationDate;
    private Long cartId;
    private Long incomeOrderId;
}
