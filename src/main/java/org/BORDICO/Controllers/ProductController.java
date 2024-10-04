package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.DTO.ProductDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.ProductInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.ProductService;
import org.BORDICO.Exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductInput productInput) {
        ProductDTO createdProduct = productService.createProduct(productInput);
        return ResponseEntity.ok(createdProduct);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<ProductDTO>> getAllProducts(PageInput pageInput) {
        PageOutput<ProductDTO> productPage = productService.getAllProducts(pageInput);
        return ResponseEntity.ok(productPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) throws CustomException {
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductInput productInput) throws CustomException {
        ProductDTO updatedProduct = productService.updateProduct(id, productInput);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws CustomException {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with ID " + id + " was deleted successfully");
    }
}