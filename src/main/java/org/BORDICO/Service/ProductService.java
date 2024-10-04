package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ProductDTO;
import org.BORDICO.Model.Entity.Product;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.ProductInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.ProductRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    public ProductDTO createProduct(ProductInput productInput) throws CustomException {
        Product product = new Product();
        return getProductFromInput(productInput, product);
    }
    public PageOutput<ProductDTO> getAllProducts(PageInput pageInput) {
        Page<Product> products = productRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                products.getNumber(),
                products.getTotalPages(),
                products.getTotalElements(),
                products.getContent().stream()
                        .map(product -> modelMapperUtil.map(product, ProductDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public ProductDTO getProductById(Long id) throws CustomException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product with id " + id + " not found"));
        return modelMapper.map(product, ProductDTO.class);
    }
    public ProductDTO updateProduct(Long id, ProductInput productInput) throws CustomException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product with id " + id + " not found"));
        return getProductFromInput(productInput, product);
    }
    public void deleteProduct(Long id) throws CustomException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product with id " + id + " not found"));
        productRepository.delete(product);
    }
    private ProductDTO getProductFromInput(ProductInput productInput, Product product) {
        product.setProductName(productInput.getProductName());
        product.setProductPrice(productInput.getProductPrice());
        product.setProductWidth(productInput.getProductWidth());
        product.setProductHeight(productInput.getProductHeight());
        product.setProductLength(productInput.getProductLength());
        product.setProductWeight(productInput.getProductWeight());
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
}