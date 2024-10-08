package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.PaymentDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.PaymentInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentInput paymentInput) throws CustomException {
        PaymentDTO createdPayment = paymentService.createPayment(paymentInput);
        return ResponseEntity.ok(createdPayment);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<PaymentDTO>> getAllPayments(PageInput pageInput) {
        PageOutput<PaymentDTO> paymentsPage = paymentService.getAllPayments(pageInput);
        return ResponseEntity.ok(paymentsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) throws CustomException {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentInput paymentInput) throws CustomException {
        PaymentDTO updatedPayment = paymentService.updatePayment(id, paymentInput);
        return ResponseEntity.ok(updatedPayment);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) throws CustomException {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment with ID " + id + " was deleted successfully");
    }
}