package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.InventoryDTO;
import org.BORDICO.Model.Entity.Inventory;
import org.BORDICO.Model.Inputs.InventoryInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.InventoryRepository;
import org.BORDICO.Repository.ProductRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final ProductRepository productRepository;
    public InventoryDTO createInventory(InventoryInput inventoryInput) throws CustomException {
        Inventory inventory = new Inventory();
        return getInventoryFromInput(inventoryInput, inventory);
    }
    public PageOutput<InventoryDTO> getAllInventories(PageInput pageInput) {
        Page<Inventory> inventories = inventoryRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                inventories.getNumber(),
                inventories.getTotalPages(),
                inventories.getTotalElements(),
                inventories.getContent().stream()
                        .map(inventory -> modelMapperUtil.map(inventory, InventoryDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public InventoryDTO getInventoryById(Long id) throws CustomException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Inventory with id " + id + " not found"));
        return modelMapper.map(inventory, InventoryDTO.class);
    }
    public InventoryDTO updateInventory(Long id, InventoryInput inventoryInput) throws CustomException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Inventory with id " + id + " not found"));
        return getInventoryFromInput(inventoryInput, inventory);
    }
    public void deleteInventory(Long id) throws CustomException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Inventory with id " + id + " not found"));
        inventoryRepository.delete(inventory);
    }
    private InventoryDTO getInventoryFromInput(InventoryInput inventoryInput, Inventory inventory) throws CustomException {
        if (!productRepository.existsByProductName(inventoryInput.getProductName())) {
            throw new CustomException("Product must be registered before adding item");
        }
        inventory.setProductName(inventoryInput.getProductName());
        inventory.setProductColorType(inventoryInput.getProductColorType());
        inventory.setIsSold(inventoryInput.getIsSold());
        inventory.setManufacturedDate(inventoryInput.getManufacturedDate());
        if (inventoryInput.getIsSold()) {
            inventory.setSoldAt(inventoryInput.getSoldAt());
        } else {
            inventory.setSoldAt(null);
        }
        inventory = inventoryRepository.save(inventory);
        return modelMapper.map(inventory, InventoryDTO.class);
    }
}