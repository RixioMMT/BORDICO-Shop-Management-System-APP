package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.FAQDTO;
import org.BORDICO.Model.Inputs.FAQInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.FAQService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faqs")
@RequiredArgsConstructor
public class FAQController {
    private final FAQService faqService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FAQDTO> createFAQ(@RequestBody FAQInput faqInput) throws CustomException {
        FAQDTO createdFAQ = faqService.createFAQ(faqInput);
        return ResponseEntity.ok(createdFAQ);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FAQDTO> getFAQById(@PathVariable Long id) throws CustomException {
        FAQDTO faqDTO = faqService.getFAQById(id);
        return ResponseEntity.ok(faqDTO);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<FAQDTO>> getAllFAQs(PageInput pageInput) {
        PageOutput<FAQDTO> faqsPage = faqService.getAllFAQs(pageInput);
        return ResponseEntity.ok(faqsPage);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FAQDTO> updateFAQ(@PathVariable Long id, @RequestBody FAQInput faqInput) throws CustomException {
        FAQDTO updatedFAQ = faqService.updateFAQ(id, faqInput);
        return ResponseEntity.ok(updatedFAQ);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteFAQ(@PathVariable Long id) throws CustomException {
        faqService.deleteFAQ(id);
        return ResponseEntity.ok("FAQ with ID " + id + " was deleted successfully");
    }
}