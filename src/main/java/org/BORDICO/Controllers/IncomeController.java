package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.IncomeDTO;
import org.BORDICO.Model.Inputs.IncomeInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeInput incomeInput) throws CustomException {
        IncomeDTO createdIncome = incomeService.createIncome(incomeInput);
        return ResponseEntity.ok(createdIncome);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<IncomeDTO>> getAllIncomes(PageInput pageInput) {
        PageOutput<IncomeDTO> incomesPage = incomeService.getAllIncomes(pageInput);
        return ResponseEntity.ok(incomesPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<IncomeDTO> getIncomeById(@PathVariable Long id) throws CustomException {
        IncomeDTO incomeDTO = incomeService.getIncomeById(id);
        return ResponseEntity.ok(incomeDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id, @RequestBody IncomeInput incomeInput) throws CustomException {
        IncomeDTO updatedIncome = incomeService.updateIncome(id, incomeInput);
        return ResponseEntity.ok(updatedIncome);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) throws CustomException {
        incomeService.deleteIncome(id);
        return ResponseEntity.ok("Income with ID " + id + " was deleted successfully");
    }
}