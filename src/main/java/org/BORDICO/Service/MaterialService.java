package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.MaterialDTO;
import org.BORDICO.Model.Entity.Material;
import org.BORDICO.Model.Entity.Pattern;
import org.BORDICO.Model.Inputs.MaterialInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.MaterialRepository;
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
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    public MaterialDTO createMaterial(MaterialInput materialInput) {
        Material material = new Material();
        Set<Pattern> patterns = new HashSet<>();
        material.setPatterns(patterns);
        return getMaterialFromInput(materialInput, material);
    }
    public PageOutput<MaterialDTO> getAllMaterials(PageInput pageInput) {
        Page<Material> materials = materialRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                materials.getNumber(),
                materials.getTotalPages(),
                materials.getTotalElements(),
                materials.getContent().stream()
                        .map(material -> modelMapperUtil.map(material, MaterialDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public MaterialDTO getMaterialById(Long id) throws CustomException {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new CustomException("Material with id " + id + " not found"));
        return modelMapper.map(material, MaterialDTO.class);
    }
    public MaterialDTO updateMaterial(Long id, MaterialInput materialInput) throws CustomException {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new CustomException("Material with id " + id + " not found"));
        return getMaterialFromInput(materialInput, material);
    }
    public void deleteMaterial(Long id) throws CustomException {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new CustomException("Material with id " + id + " not found"));
        materialRepository.delete(material);
    }
    private MaterialDTO getMaterialFromInput(MaterialInput materialInput, Material material) {
        material.setSupplyName(materialInput.getSupplyName());
        material.setSupplyIsYarn(materialInput.getSupplyIsYarn());
        if (materialInput.getSupplyIsYarn()) {
            material.setYarnGrams(materialInput.getYarnGrams());
        } else {
            material.setYarnGrams(null);
        }
        material = materialRepository.save(material);
        return modelMapper.map(material, MaterialDTO.class);
    }
}