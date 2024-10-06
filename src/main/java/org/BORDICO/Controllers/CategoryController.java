package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.CategoryDTO;
import org.BORDICO.Model.Inputs.CategoryInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryInput categoryInput) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryInput);
        return ResponseEntity.ok(createdCategory);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<CategoryDTO>> getAllCategories(PageInput pageInput) {
        PageOutput<CategoryDTO> categoriesPage = categoryService.getAllCategories(pageInput);
        return ResponseEntity.ok(categoriesPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) throws CustomException {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryInput categoryInput) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryInput);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws CustomException {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category with ID " + id + " was deleted successfully");
    }
}