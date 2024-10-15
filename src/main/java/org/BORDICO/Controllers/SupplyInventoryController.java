package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.SupplyInventoryDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.SupplyInventoryInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.SupplyInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplies-inventory")
@RequiredArgsConstructor
public class SupplyInventoryController {
    private final SupplyInventoryService supplyInventoryService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplyInventoryDTO> createSupplyInventory(@RequestBody SupplyInventoryInput supplyInventoryInput) throws CustomException {
        SupplyInventoryDTO createdSupplyInventory = supplyInventoryService.createSupplyInventory(supplyInventoryInput);
        return ResponseEntity.ok(createdSupplyInventory);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplyInventoryDTO> getSupplyInventoryById(@PathVariable Long id) throws CustomException {
        SupplyInventoryDTO supplyInventoryDTO = supplyInventoryService.getSupplyInventoryById(id);
        return ResponseEntity.ok(supplyInventoryDTO);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<SupplyInventoryDTO>> getAllSupplyInventories(PageInput pageInput) {
        PageOutput<SupplyInventoryDTO> supplyInventoriesPage = supplyInventoryService.getAllSupplyInventories(pageInput);
        return ResponseEntity.ok(supplyInventoriesPage);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SupplyInventoryDTO> updateSupplyInventory(@PathVariable Long id, @RequestBody SupplyInventoryInput supplyInventoryInput) throws CustomException {
        SupplyInventoryDTO updatedSupplyInventory = supplyInventoryService.updateSupplyInventory(id, supplyInventoryInput);
        return ResponseEntity.ok(updatedSupplyInventory);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteSupplyInventory(@PathVariable Long id) throws CustomException {
        supplyInventoryService.deleteSupplyInventory(id);
        return ResponseEntity.ok("Supply Inventory with ID " + id + " was deleted successfully");
    }
}