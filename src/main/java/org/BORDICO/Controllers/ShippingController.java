package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ShippingDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.ShippingInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.ShippingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippings")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService shippingService;
    @PostMapping
    public ResponseEntity<ShippingDTO> createShipping(@RequestBody ShippingInput shippingInput) throws CustomException {
        ShippingDTO createdShipping = shippingService.createShipping(shippingInput);
        return ResponseEntity.ok(createdShipping);
    }
    @GetMapping
    public ResponseEntity<PageOutput<ShippingDTO>> getAllShippings(PageInput pageInput) {
        PageOutput<ShippingDTO> shippingsPage = shippingService.getAllShippings(pageInput);
        return ResponseEntity.ok(shippingsPage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShippingDTO> getShippingById(@PathVariable Long id) throws CustomException {
        ShippingDTO shippingDTO = shippingService.getShippingById(id);
        return ResponseEntity.ok(shippingDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShippingDTO> updateShipping(@PathVariable Long id, @RequestBody ShippingInput shippingInput) throws CustomException {
        ShippingDTO updatedShipping = shippingService.updateShipping(id, shippingInput);
        return ResponseEntity.ok(updatedShipping);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable Long id) throws CustomException {
        shippingService.deleteShipping(id);
        return ResponseEntity.ok("Shipping with ID " + id + " was deleted successfully");
    }
}