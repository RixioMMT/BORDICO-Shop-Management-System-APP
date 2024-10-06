package org.BORDICO.Model.DTO;

import lombok.Data;
import java.util.Set;

@Data
public class CategoryDTO {
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private Set<String> productNames;
}
