package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ReviewDTO;
import org.BORDICO.Model.Entity.Product;
import org.BORDICO.Model.Entity.Review;
import org.BORDICO.Model.Entity.User;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.ReviewInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.ProductRepository;
import org.BORDICO.Repository.ReviewRepository;
import org.BORDICO.Repository.UserRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    public ReviewDTO createReview(ReviewInput reviewInput) throws CustomException {
        Review review = new Review();
        return getReviewFromInput(reviewInput, review);
    }
    public PageOutput<ReviewDTO> getAllReviews(PageInput pageInput) {
        Page<Review> reviews = reviewRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                reviews.getNumber(),
                reviews.getTotalPages(),
                reviews.getTotalElements(),
                reviews.getContent().stream()
                        .map(review -> modelMapperUtil.map(review, ReviewDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public ReviewDTO getReviewById(Long id) throws CustomException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new CustomException("Review with ID " + id + " not found"));
        return modelMapper.map(review, ReviewDTO.class);
    }
    public ReviewDTO updateReview(Long id, ReviewInput reviewInput) throws CustomException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new CustomException("Review with ID " + id + " not found"));
        return getReviewFromInput(reviewInput, review);
    }
    public void deleteReview(Long id) throws CustomException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new CustomException("Review with ID " + id + " not found"));
        reviewRepository.delete(review);
    }
    private ReviewDTO getReviewFromInput(ReviewInput reviewInput, Review review) throws CustomException {
        Product product = productRepository.findById(reviewInput.getProductId())
                .orElseThrow(() -> new CustomException("Product with ID " + reviewInput.getProductId() + " not found"));
        User user = userRepository.findById(reviewInput.getUserId())
                .orElseThrow(() -> new CustomException("User with ID " + reviewInput.getUserId() + " not found"));
        review.setTitle(reviewInput.getTitle());
        review.setDescription(reviewInput.getDescription());
        review.setReviewCategory(reviewInput.getReviewCategory());
        review.setProduct(product);
        review.setUser(user);
        review = reviewRepository.save(review);
        return modelMapper.map(review, ReviewDTO.class);
    }
}