package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.PatternDTO;
import org.BORDICO.Model.Entity.Pattern;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.PatternInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.PatternRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatternService {
    private final PatternRepository patternRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    public PatternDTO createPattern(PatternInput patternInput) {
        Pattern pattern = new Pattern();
        pattern.setPatternImageUrl(null);
        pattern.setPatternPdfUrl(null);
        pattern.setProducts(new HashSet<>());
        pattern.setMaterials(new HashSet<>());
        return getPatternFromInput(patternInput, pattern);
    }
    public PageOutput<PatternDTO> getAllPatterns(PageInput pageInput) {
        Page<Pattern> patterns = patternRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                patterns.getNumber(),
                patterns.getTotalPages(),
                patterns.getTotalElements(),
                patterns.getContent().stream()
                        .map(pattern -> modelMapperUtil.map(pattern, PatternDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public PatternDTO getPatternById(Long id) throws CustomException {
        Pattern pattern = patternRepository.findById(id)
                .orElseThrow(() -> new CustomException("Pattern with id " + id + " not found"));
        return modelMapper.map(pattern, PatternDTO.class);
    }
    public PatternDTO updatePattern(Long id, PatternInput patternInput) throws CustomException {
        Pattern pattern = patternRepository.findById(id)
                .orElseThrow(() -> new CustomException("Pattern with id " + id + " not found"));
        return getPatternFromInput(patternInput, pattern);
    }
    public void deletePattern(Long id) throws CustomException {
        Pattern pattern = patternRepository.findById(id)
                .orElseThrow(() -> new CustomException("Pattern with id " + id + " not found"));
        patternRepository.delete(pattern);
    }
    private PatternDTO getPatternFromInput(PatternInput patternInput, Pattern pattern) {
        pattern.setPatternName(patternInput.getPatternName());
        pattern = patternRepository.save(pattern);
        return modelMapper.map(pattern, PatternDTO.class);
    }
}