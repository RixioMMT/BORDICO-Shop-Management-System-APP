package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.InventoryDTO;
import org.BORDICO.Model.Inputs.InventoryInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryInput inventoryInput) throws CustomException {
        InventoryDTO createdInventory = inventoryService.createInventory(inventoryInput);
        return ResponseEntity.ok(createdInventory);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<InventoryDTO>> getAllInventories(PageInput pageInput) {
        PageOutput<InventoryDTO> inventoriesPage = inventoryService.getAllInventories(pageInput);
        return ResponseEntity.ok(inventoriesPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) throws CustomException {
        InventoryDTO inventoryDTO = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventoryDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id, @RequestBody InventoryInput inventoryInput) throws CustomException {
        InventoryDTO updatedInventory = inventoryService.updateInventory(id, inventoryInput);
        return ResponseEntity.ok(updatedInventory);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) throws CustomException {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory with ID " + id + " was deleted successfully");
    }
}