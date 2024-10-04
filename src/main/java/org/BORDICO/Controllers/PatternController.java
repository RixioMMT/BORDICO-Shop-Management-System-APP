package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.PatternDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.PatternInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.PatternService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patterns")
@RequiredArgsConstructor
public class PatternController {
    private final PatternService patternService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PatternDTO> createPattern(@RequestBody PatternInput patternInput) {
        PatternDTO createdPattern = patternService.createPattern(patternInput);
        return ResponseEntity.ok(createdPattern);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<PatternDTO>> getAllPatterns(PageInput pageInput) {
        PageOutput<PatternDTO> patternsPage = patternService.getAllPatterns(pageInput);
        return ResponseEntity.ok(patternsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PatternDTO> getPatternById(@PathVariable Long id) throws CustomException {
        PatternDTO patternDTO = patternService.getPatternById(id);
        return ResponseEntity.ok(patternDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PatternDTO> updatePattern(@PathVariable Long id, @RequestBody PatternInput patternInput) throws CustomException {
        PatternDTO updatedPattern = patternService.updatePattern(id, patternInput);
        return ResponseEntity.ok(updatedPattern);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletePattern(@PathVariable Long id) throws CustomException {
        patternService.deletePattern(id);
        return ResponseEntity.ok("Pattern with ID " + id + " was deleted successfully");
    }
}