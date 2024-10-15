package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ProductInventoryDTO;
import org.BORDICO.Model.Entity.Product;
import org.BORDICO.Model.Entity.ProductInventory;
import org.BORDICO.Model.Inputs.ProductInventoryInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.ProductInventoryRepository;
import org.BORDICO.Repository.ProductRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductInventoryService {
    private final ProductInventoryRepository productInventoryRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final ProductRepository productRepository;
    public ProductInventoryDTO createInventory(ProductInventoryInput productInventoryInput) throws CustomException {
        ProductInventory productInventory = new ProductInventory();

        return getInventoryFromInput(productInventoryInput, productInventory);
    }
    public PageOutput<ProductInventoryDTO> getAllInventories(PageInput pageInput) {
        Page<ProductInventory> inventories = productInventoryRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                inventories.getNumber(),
                inventories.getTotalPages(),
                inventories.getTotalElements(),
                inventories.getContent().stream()
                        .map(productInventory -> modelMapperUtil.map(productInventory, ProductInventoryDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public ProductInventoryDTO getInventoryById(Long id) throws CustomException {
        ProductInventory productInventory = productInventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Inventory with id " + id + " not found"));
        return modelMapper.map(productInventory, ProductInventoryDTO.class);
    }
    public ProductInventoryDTO updateInventory(Long id, ProductInventoryInput productInventoryInput) throws CustomException {
        ProductInventory productInventory = productInventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Inventory with id " + id + " not found"));
        return getInventoryFromInput(productInventoryInput, productInventory);
    }
    public void deleteInventory(Long id) throws CustomException {
        ProductInventory productInventory = productInventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Inventory with id " + id + " not found"));
        productInventoryRepository.delete(productInventory);
    }
    private ProductInventoryDTO getInventoryFromInput(ProductInventoryInput productInventoryInput, ProductInventory productInventory) throws CustomException {
        Product product = productRepository.findById(productInventoryInput.getProductId())
                .orElseThrow(() -> new CustomException("Product with ID " + productInventoryInput.getProductId() + " must be registered before adding item"));
        productInventory.setItemName(product.getProductName());
        productInventory.setItemColorType(productInventoryInput.getItemColorType());
        productInventory.setIsSold(productInventoryInput.getIsSold());
        productInventory.setManufacturedDate(productInventoryInput.getManufacturedDate());
        if (productInventoryInput.getIsSold()) {
            productInventory.setSoldAt(productInventoryInput.getSoldAt());
        } else {
            productInventory.setSoldAt(null);
        }
        productInventory.setProduct(product);
        productInventory = productInventoryRepository.save(productInventory);
        return modelMapper.map(productInventory, ProductInventoryDTO.class);
    }
}