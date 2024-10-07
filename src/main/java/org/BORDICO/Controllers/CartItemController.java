package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.CartItemDTO;
import org.BORDICO.Model.Inputs.CartItemInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CartItemDTO> createCartItem(@RequestBody CartItemInput cartItemInput) throws CustomException {
        CartItemDTO createdCartItem = cartItemService.createCartItem(cartItemInput);
        return ResponseEntity.ok(createdCartItem);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<PageOutput<CartItemDTO>> getAllCartItems(PageInput pageInput) {
        PageOutput<CartItemDTO> cartItemsPage = cartItemService.getAllCartItems(pageInput);
        return ResponseEntity.ok(cartItemsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable Long id) throws CustomException {
        CartItemDTO cartItemDTO = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItemDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long id, @RequestBody CartItemInput cartItemInput) throws CustomException {
        CartItemDTO updatedCartItem = cartItemService.updateCartItem(id, cartItemInput);
        return ResponseEntity.ok(updatedCartItem);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long id) throws CustomException {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok("Cart Item with ID " + id + " was deleted successfully");
    }
}