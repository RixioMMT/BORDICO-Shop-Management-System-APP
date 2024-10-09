package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Entity.ProductInventory;
import org.BORDICO.Model.Enum.IncomePlatform;
import org.BORDICO.Model.Enum.PaymentMethod;

import java.util.Set;

@Data
public class IncomeOrderDTO {
    private Long id;
    private String clientName;
    private String shippingAddress;
    private Double orderPrice;
    private PaymentMethod paymentMethod;
    private IncomePlatform incomePlatform;
    private Long paymentId;
    private Long incomeId;
    private Long shippingId;
    private Set<Long> itemsInventoryId;
}
