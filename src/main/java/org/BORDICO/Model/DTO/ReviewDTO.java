package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.ReviewCategory;

@Data
public class ReviewDTO {
    private Long Id;
    private String title;
    private String description;
    private ReviewCategory reviewCategory;
    private Long productId;
    private Long userId;
}
