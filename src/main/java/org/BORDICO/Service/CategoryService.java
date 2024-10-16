package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.CategoryDTO;
import org.BORDICO.Model.Entity.Category;
import org.BORDICO.Model.Entity.Product;
import org.BORDICO.Model.Inputs.CategoryInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.CategoryRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    public CategoryDTO createCategory(CategoryInput categoryInput) {
        Category category = new Category();
        Set<Product> products = new HashSet<>();
        category.setProducts(products);
        return getCategoryFromInput(categoryInput, category);
    }
    public PageOutput<CategoryDTO> getAllCategories(PageInput pageInput) {
        Page<Category> categories = categoryRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                categories.getNumber(),
                categories.getTotalPages(),
                categories.getTotalElements(),
                categories.getContent().stream()
                        .map(category -> modelMapperUtil.map(category, CategoryDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public CategoryDTO getCategoryById(Long id) throws CustomException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Category with id " + id + " not found"));
        return modelMapper.map(category, CategoryDTO.class);
    }
    public CategoryDTO updateCategory(Long id, CategoryInput categoryInput) throws CustomException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Category with id " + id + " not found"));
        return getCategoryFromInput(categoryInput, category);
    }
    public void deleteCategory(Long id) throws CustomException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Category with id " + id + " not found"));
        categoryRepository.delete(category);
    }
    private CategoryDTO getCategoryFromInput(CategoryInput categoryInput, Category category) {
        category.setCategoryName(categoryInput.getCategoryName());
        category.setCategoryDescription(categoryInput.getCategoryDescription());
        category = categoryRepository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }
}