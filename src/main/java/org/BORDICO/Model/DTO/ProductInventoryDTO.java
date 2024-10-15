package org.BORDICO.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductInventoryDTO {
    private Long Id;
    private String itemName;
    private String itemColorType;
    private Boolean isSold;
    private LocalDateTime manufacturedDate;
    private LocalDateTime soldAt;
    private Long productId;
    private Long incomeOrderId;
}
