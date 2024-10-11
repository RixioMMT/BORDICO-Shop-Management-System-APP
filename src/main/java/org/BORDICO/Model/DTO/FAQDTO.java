package org.BORDICO.Model.DTO;

import lombok.Data;
import org.BORDICO.Model.Enum.FAQCategory;

@Data
public class FAQDTO {
    private Long id;
    private String question;
    private String answer;
    private FAQCategory faqCategory;
}
