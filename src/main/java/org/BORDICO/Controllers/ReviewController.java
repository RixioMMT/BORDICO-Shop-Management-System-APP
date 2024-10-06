package org.BORDICO.Controllers;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ReviewDTO;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.ReviewInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewInput reviewInput) throws CustomException {
        ReviewDTO createdReview = reviewService.createReview(reviewInput);
        return ResponseEntity.ok(createdReview);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PageOutput<ReviewDTO>> getAllReviews(PageInput pageInput) {
        PageOutput<ReviewDTO> reviewsPage = reviewService.getAllReviews(pageInput);
        return ResponseEntity.ok(reviewsPage);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) throws CustomException {
        ReviewDTO reviewDTO = reviewService.getReviewById(id);
        return ResponseEntity.ok(reviewDTO);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewInput reviewInput) throws CustomException {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewInput);
        return ResponseEntity.ok(updatedReview);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) throws CustomException {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review with ID " + id + " was deleted successfully");
    }
}