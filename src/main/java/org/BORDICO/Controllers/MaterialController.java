package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.MaterialDTO;
import org.BORDICO.Model.DTO.UserDTO;
import org.BORDICO.Model.Inputs.MaterialInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.MaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialInput materialInput) {
        MaterialDTO createdMaterial = materialService.createMaterial(materialInput);
        return ResponseEntity.ok(createdMaterial);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<MaterialDTO>> getAllMaterials(PageInput pageInput) {
        PageOutput<MaterialDTO> materialsPage = materialService.getAllMaterials(pageInput);
        return ResponseEntity.ok(materialsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MaterialDTO> getMaterialById(@PathVariable Long id) throws CustomException {
        MaterialDTO material = materialService.getMaterialById(id);
        return ResponseEntity.ok(material);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<MaterialDTO> updateMaterial(@PathVariable Long id, @RequestBody MaterialInput materialInput) throws CustomException {
        MaterialDTO updatedMaterial = materialService.updateMaterial(id, materialInput);
        return ResponseEntity.ok(updatedMaterial);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteMaterial(@PathVariable Long id) throws CustomException {
        materialService.deleteMaterial(id);
        return ResponseEntity.ok("Material with id " + id + " was deleted successfully."); // Send confirmation message
    }
}
