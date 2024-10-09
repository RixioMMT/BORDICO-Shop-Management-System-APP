package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.SupplyDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.SupplyInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.SupplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplies")
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplyDTO> createSupply(@RequestBody SupplyInput supplyInput) {
        SupplyDTO createdSupply = supplyService.createSupply(supplyInput);
        return ResponseEntity.ok(createdSupply);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<SupplyDTO>> getAllSupplies(PageInput pageInput) {
        PageOutput<SupplyDTO> suppliesPage = supplyService.getAllSupplies(pageInput);
        return ResponseEntity.ok(suppliesPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplyDTO> getSupplyById(@PathVariable Long id) throws CustomException {
        SupplyDTO supplyDTO = supplyService.getSupplyById(id);
        return ResponseEntity.ok(supplyDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplyDTO> updateSupply(@PathVariable Long id, @RequestBody SupplyInput supplyInput) throws CustomException {
        SupplyDTO updatedSupply = supplyService.updateSupply(id, supplyInput);
        return ResponseEntity.ok(updatedSupply);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteSupply(@PathVariable Long id) throws CustomException {
        supplyService.deleteSupply(id);
        return ResponseEntity.ok("Supply with ID " + id + " was deleted successfully");
    }
}