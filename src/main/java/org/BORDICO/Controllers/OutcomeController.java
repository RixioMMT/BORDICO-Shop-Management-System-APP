package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.OutcomeDTO;
import org.BORDICO.Model.Inputs.OutcomeInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.OutcomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outcomes")
@RequiredArgsConstructor
public class OutcomeController {
    private final OutcomeService outcomeService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OutcomeDTO> createOutcome(@RequestBody OutcomeInput outcomeInput) throws CustomException {
        OutcomeDTO createdOutcome = outcomeService.createOutcome(outcomeInput);
        return ResponseEntity.ok(createdOutcome);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<OutcomeDTO>> getAllOutcomes(PageInput pageInput) {
        PageOutput<OutcomeDTO> outcomesPage = outcomeService.getAllOutcomes(pageInput);
        return ResponseEntity.ok(outcomesPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OutcomeDTO> getOutcomeById(@PathVariable Long id) throws CustomException {
        OutcomeDTO outcomeDTO = outcomeService.getOutcomeById(id);
        return ResponseEntity.ok(outcomeDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OutcomeDTO> updateOutcome(@PathVariable Long id, @RequestBody OutcomeInput outcomeInput) throws CustomException {
        OutcomeDTO updatedOutcome = outcomeService.updateOutcome(id, outcomeInput);
        return ResponseEntity.ok(updatedOutcome);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteOutcome(@PathVariable Long id) throws CustomException {
        outcomeService.deleteOutcome(id);
        return ResponseEntity.ok("Outcome with ID " + id + " was deleted successfully");
    }
}