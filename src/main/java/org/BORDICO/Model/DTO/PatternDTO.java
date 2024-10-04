package org.BORDICO.Model.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class PatternDTO {
    private Long id;
    private String patternName;
    private String patternImageUrl;
    private String patternPdfUrl;
    private Set<String> productNames;
    private Set<String> materialNames;
}
