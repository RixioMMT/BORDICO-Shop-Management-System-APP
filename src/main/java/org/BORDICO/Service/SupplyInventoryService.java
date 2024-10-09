package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.SupplyInventoryDTO;
import org.BORDICO.Model.Entity.OutcomeOrder;
import org.BORDICO.Model.Entity.SupplyInventory;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.SupplyInventoryInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.OutcomeOrderRepository;
import org.BORDICO.Repository.SupplyInventoryRepository;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyInventoryService {
    private final SupplyInventoryRepository supplyInventoryRepository;
    private final OutcomeOrderRepository outcomeOrderRepository;
    private final ModelMapper modelMapper;
    public SupplyInventoryDTO createSupplyInventory(SupplyInventoryInput supplyInventoryInput) throws CustomException {
        SupplyInventory supplyInventory = new SupplyInventory();
        return getSupplyInventoryFromInput(supplyInventoryInput, supplyInventory);
    }
    public SupplyInventoryDTO getSupplyInventoryById(Long id) throws CustomException {
        SupplyInventory supplyInventory = supplyInventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Supply Inventory with ID " + id + " not found"));
        return modelMapper.map(supplyInventory, SupplyInventoryDTO.class);
    }
    public PageOutput<SupplyInventoryDTO> getAllSupplyInventories(PageInput pageInput) {
        Page<SupplyInventory> supplyInventories = supplyInventoryRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                supplyInventories.getNumber(),
                supplyInventories.getTotalPages(),
                supplyInventories.getTotalElements(),
                supplyInventories.getContent().stream()
                        .map(supplyInventory -> modelMapper.map(supplyInventory, SupplyInventoryDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public SupplyInventoryDTO updateSupplyInventory(Long id, SupplyInventoryInput supplyInventoryInput) throws CustomException {
        SupplyInventory supplyInventory = supplyInventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Supply Inventory with ID " + id + " not found"));
        return getSupplyInventoryFromInput(supplyInventoryInput, supplyInventory);
    }
    public void deleteSupplyInventory(Long id) throws CustomException {
        SupplyInventory supplyInventory = supplyInventoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Supply Inventory with ID " + id + " not found"));
        supplyInventoryRepository.delete(supplyInventory);
    }
    private SupplyInventoryDTO getSupplyInventoryFromInput(SupplyInventoryInput supplyInventoryInput, SupplyInventory supplyInventory) throws CustomException {
        OutcomeOrder outcomeOrder = outcomeOrderRepository.findById(supplyInventoryInput.getOutcomeOrderId())
                .orElseThrow(() -> new CustomException("Outcome Order with ID " + supplyInventoryInput.getOutcomeOrderId() + " not found"));
        supplyInventory.setSupplyName(supplyInventoryInput.getSupplyName());
        supplyInventory.setSupplyPrice(supplyInventoryInput.getSupplyPrice());
        supplyInventory.setSupplyIsYarn(supplyInventoryInput.getSupplyIsYarn());
        supplyInventory.setYarnGrams(supplyInventoryInput.getYarnGrams());
        supplyInventory.setSupplyBrand(supplyInventoryInput.getSupplyBrand());
        supplyInventory.setOutcomeOrder(outcomeOrder);
        supplyInventory = supplyInventoryRepository.save(supplyInventory);
        return modelMapper.map(supplyInventory, SupplyInventoryDTO.class);
    }
}