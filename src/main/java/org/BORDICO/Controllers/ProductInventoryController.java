package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ProductInventoryDTO;
import org.BORDICO.Model.Inputs.ProductInventoryInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.ProductInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class ProductInventoryController {
    private final ProductInventoryService productInventoryService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductInventoryDTO> createInventory(@RequestBody ProductInventoryInput productInventoryInput) throws CustomException {
        ProductInventoryDTO createdInventory = productInventoryService.createInventory(productInventoryInput);
        return ResponseEntity.ok(createdInventory);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<ProductInventoryDTO>> getAllInventories(PageInput pageInput) {
        PageOutput<ProductInventoryDTO> inventoriesPage = productInventoryService.getAllInventories(pageInput);
        return ResponseEntity.ok(inventoriesPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductInventoryDTO> getInventoryById(@PathVariable Long id) throws CustomException {
        ProductInventoryDTO productInventoryDTO = productInventoryService.getInventoryById(id);
        return ResponseEntity.ok(productInventoryDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductInventoryDTO> updateInventory(@PathVariable Long id, @RequestBody ProductInventoryInput productInventoryInput) throws CustomException {
        ProductInventoryDTO updatedInventory = productInventoryService.updateInventory(id, productInventoryInput);
        return ResponseEntity.ok(updatedInventory);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) throws CustomException {
        productInventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory with ID " + id + " was deleted successfully");
    }
}