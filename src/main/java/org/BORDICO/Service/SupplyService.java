package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.SupplyDTO;
import org.BORDICO.Model.Entity.Supply;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.SupplyInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.SupplyRepository;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    private final ModelMapper modelMapper;
    public SupplyDTO createSupply(SupplyInput supplyInput) {
        Supply supply = new Supply();
        return getSupplyFromInput(supplyInput, supply);
    }
    public PageOutput<SupplyDTO> getAllSupplies(PageInput pageInput) {
        Page<Supply> supplies = supplyRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                supplies.getNumber(),
                supplies.getTotalPages(),
                supplies.getTotalElements(),
                supplies.getContent().stream()
                        .map(supply -> modelMapper.map(supply, SupplyDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public SupplyDTO getSupplyById(Long id) throws CustomException {
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Supply with ID " + id + " not found"));
        return modelMapper.map(supply, SupplyDTO.class);
    }
    public SupplyDTO updateSupply(Long id, SupplyInput supplyInput) throws CustomException {
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Supply with ID " + id + " not found"));
        return getSupplyFromInput(supplyInput, supply);
    }
    public void deleteSupply(Long id) throws CustomException {
        Supply supply = supplyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Supply with ID " + id + " not found"));
        supplyRepository.delete(supply);
    }
    private SupplyDTO getSupplyFromInput(SupplyInput supplyInput, Supply supply) {
        supply.setSupplyName(supplyInput.getSupplyName());
        supply.setSupplyPrice(supplyInput.getSupplyPrice());
        supply.setSupplyQuantity(supplyInput.getSupplyQuantity());
        supply.setSupplyIsYarn(supplyInput.getSupplyIsYarn());
        supply.setYarnGrams(supplyInput.getYarnGrams());
        supply.setSupplyBrand(supplyInput.getSupplyBrand());
        supply = supplyRepository.save(supply);
        return modelMapper.map(supply, SupplyDTO.class);
    }
}