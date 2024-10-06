package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryInput {
    private String productName;
    private String productColorType;
    private Boolean isSold;
    private LocalDateTime manufacturedDate;
    private LocalDateTime soldAt;
}
