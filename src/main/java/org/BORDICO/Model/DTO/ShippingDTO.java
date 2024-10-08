package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.ShippingStatus;

import java.time.LocalDateTime;

@Data
public class ShippingDTO {
    private Long id;
    private String carrier;
    private String trackingNumber;
    private LocalDateTime shippingDate;
    private ShippingStatus shippingStatus;
    private Long incomeOrder;
}
