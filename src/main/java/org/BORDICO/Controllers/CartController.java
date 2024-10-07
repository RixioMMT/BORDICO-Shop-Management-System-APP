package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.CartDTO;
import org.BORDICO.Model.Inputs.CartInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CartDTO> createCart(@RequestBody CartInput cartInput) throws CustomException {
        CartDTO createdCart = cartService.createCart(cartInput);
        return ResponseEntity.ok(createdCart);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<CartDTO>> getAllCarts(PageInput pageInput) {
        PageOutput<CartDTO> cartsPage = cartService.getAllCarts(pageInput);
        return ResponseEntity.ok(cartsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) throws CustomException {
        CartDTO cartDTO = cartService.getCartById(id);
        return ResponseEntity.ok(cartDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartInput cartInput) throws CustomException {
        CartDTO updatedCart = cartService.updateCart(id, cartInput);
        return ResponseEntity.ok(updatedCart);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) throws CustomException {
        cartService.deleteCart(id);
        return ResponseEntity.ok("Cart with ID " + id + " was deleted successfully");
    }
}