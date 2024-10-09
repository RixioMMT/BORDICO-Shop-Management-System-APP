package org.BORDICO.Model.Inputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductInventoryInput {
    private String itemName;
    private String itemColorType;
    private Boolean isSold;
    private LocalDateTime manufacturedDate;
    private LocalDateTime soldAt;
}
