package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.FAQDTO;
import org.BORDICO.Model.Entity.FAQ;
import org.BORDICO.Model.Inputs.FAQInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.FAQRepository;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FAQService {
    private final FAQRepository faqRepository;
    private final ModelMapper modelMapper;
    public FAQDTO createFAQ(FAQInput faqInput) throws CustomException {
        FAQ faq = new FAQ();
        return getFAQFromInput(faqInput, faq);
    }
    public FAQDTO getFAQById(Long id) throws CustomException {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new CustomException("FAQ with ID " + id + " not found"));
        return modelMapper.map(faq, FAQDTO.class);
    }
    public PageOutput<FAQDTO> getAllFAQs(PageInput pageInput) {
        Page<FAQ> faqs = faqRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                faqs.getNumber(),
                faqs.getTotalPages(),
                faqs.getTotalElements(),
                faqs.getContent().stream()
                        .map(faq -> modelMapper.map(faq, FAQDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public FAQDTO updateFAQ(Long id, FAQInput faqInput) throws CustomException {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new CustomException("FAQ with ID " + id + " not found"));
        return getFAQFromInput(faqInput, faq);
    }
    public void deleteFAQ(Long id) throws CustomException {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new CustomException("FAQ with ID " + id + " not found"));
        faqRepository.delete(faq);
    }
    private FAQDTO getFAQFromInput(FAQInput faqInput, FAQ faq) {
        faq.setQuestion(faqInput.getQuestion());
        faq.setAnswer(faqInput.getAnswer());
        faq.setFaqCategory(faqInput.getFaqCategory());
        faq = faqRepository.save(faq);
        return modelMapper.map(faq, FAQDTO.class);
    }
}