package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.OutcomeOrderDTO;
import org.BORDICO.Model.Inputs.OutcomeOrderInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.OutcomeOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outcome-orders")
@RequiredArgsConstructor
public class OutcomeOrderController {
    private final OutcomeOrderService outcomeOrderService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OutcomeOrderDTO> createOutcomeOrder(@RequestBody OutcomeOrderInput outcomeOrderInput) throws CustomException {
        OutcomeOrderDTO createdOutcomeOrder = outcomeOrderService.createOutcomeOrder(outcomeOrderInput);
        return ResponseEntity.ok(createdOutcomeOrder);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<OutcomeOrderDTO>> getAllOutcomeOrders(PageInput pageInput) {
        PageOutput<OutcomeOrderDTO> outcomeOrdersPage = outcomeOrderService.getAllOutcomeOrders(pageInput);
        return ResponseEntity.ok(outcomeOrdersPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OutcomeOrderDTO> getOutcomeOrderById(@PathVariable Long id) throws CustomException {
        OutcomeOrderDTO outcomeOrderDTO = outcomeOrderService.getOutcomeOrderById(id);
        return ResponseEntity.ok(outcomeOrderDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OutcomeOrderDTO> updateOutcomeOrder(@PathVariable Long id, @RequestBody OutcomeOrderInput outcomeOrderInput) throws CustomException {
        OutcomeOrderDTO updatedOutcomeOrder = outcomeOrderService.updateOutcomeOrder(id, outcomeOrderInput);
        return ResponseEntity.ok(updatedOutcomeOrder);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteOutcomeOrder(@PathVariable Long id) throws CustomException {
        outcomeOrderService.deleteOutcomeOrder(id);
        return ResponseEntity.ok("Outcome Order with ID " + id + " was deleted successfully");
    }
}