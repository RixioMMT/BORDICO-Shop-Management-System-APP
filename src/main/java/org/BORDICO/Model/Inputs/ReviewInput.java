package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.ReviewCategory;

@Data
public class ReviewInput {
    private String title;
    private String description;
    private ReviewCategory reviewCategory;
    private Long productId;
    private Long userId;
}
