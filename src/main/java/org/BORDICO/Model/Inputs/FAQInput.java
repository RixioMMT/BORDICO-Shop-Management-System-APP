package org.BORDICO.Model.Inputs;

import lombok.Data;
import org.BORDICO.Model.Enum.FAQCategory;

@Data
public class FAQInput {
    private String question;
    private String answer;
    private FAQCategory faqCategory;
}
