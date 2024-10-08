package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.ShippingStatus;

import java.time.LocalDateTime;

@Data
public class ShippingInput {
    private String carrier;
    private String trackingNumber;
    private LocalDateTime shippingDate;
    private ShippingStatus shippingStatus;
    private Long incomeOrder;
}
