package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.IncomeOrderDTO;
import org.BORDICO.Model.Inputs.IncomeOrderInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.IncomeOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income-orders")
@RequiredArgsConstructor
public class IncomeOrderController {
    private final IncomeOrderService incomeOrderService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<IncomeOrderDTO> createIncomeOrder(@RequestBody IncomeOrderInput incomeOrderInput) throws CustomException {
        IncomeOrderDTO createdIncomeOrder = incomeOrderService.createIncomeOrder(incomeOrderInput);
        return ResponseEntity.ok(createdIncomeOrder);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<IncomeOrderDTO>> getAllIncomeOrders(PageInput pageInput) {
        PageOutput<IncomeOrderDTO> incomeOrdersPage = incomeOrderService.getAllIncomeOrders(pageInput);
        return ResponseEntity.ok(incomeOrdersPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<IncomeOrderDTO> getIncomeOrderById(@PathVariable Long id) throws CustomException {
        IncomeOrderDTO incomeOrderDTO = incomeOrderService.getIncomeOrderById(id);
        return ResponseEntity.ok(incomeOrderDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<IncomeOrderDTO> updateIncomeOrder(@PathVariable Long id, @RequestBody IncomeOrderInput incomeOrderInput) throws CustomException {
        IncomeOrderDTO updatedIncomeOrder = incomeOrderService.updateIncomeOrder(id, incomeOrderInput);
        return ResponseEntity.ok(updatedIncomeOrder);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteIncomeOrder(@PathVariable Long id) throws CustomException {
        incomeOrderService.deleteIncomeOrder(id);
        return ResponseEntity.ok("IncomeOrder with ID " + id + " was deleted successfully");
    }
}