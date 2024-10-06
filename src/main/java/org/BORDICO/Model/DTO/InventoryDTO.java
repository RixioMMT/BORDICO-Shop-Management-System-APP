package org.BORDICO.Model.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryDTO {
    private Long Id;
    private String productName;
    private String productColorType;
    private Boolean isSold;
    private LocalDateTime manufacturedDate;
    private LocalDateTime soldAt;
}
