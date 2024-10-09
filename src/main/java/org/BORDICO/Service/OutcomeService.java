package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.OutcomeDTO;
import org.BORDICO.Model.Entity.Outcome;
import org.BORDICO.Model.Entity.OutcomeOrder;
import org.BORDICO.Model.Inputs.OutcomeInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.OutcomeOrderRepository;
import org.BORDICO.Repository.OutcomeRepository;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutcomeService {
    private final OutcomeRepository outcomeRepository;
    private final ModelMapper modelMapper;
    private final OutcomeOrderRepository outcomeOrderRepository;
    public OutcomeDTO createOutcome(OutcomeInput outcomeInput) throws CustomException {
        Outcome outcome = new Outcome();
        return getOutcomeFromInput(outcomeInput, outcome);
    }
    public PageOutput<OutcomeDTO> getAllOutcomes(PageInput pageInput) {
        Page<Outcome> outcomes = outcomeRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                outcomes.getNumber(),
                outcomes.getTotalPages(),
                outcomes.getTotalElements(),
                outcomes.getContent().stream()
                        .map(outcome -> modelMapper.map(outcome, OutcomeDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public OutcomeDTO getOutcomeById(Long id) throws CustomException {
        Outcome outcome = outcomeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Outcome with ID " + id + " not found"));
        return modelMapper.map(outcome, OutcomeDTO.class);
    }
    public OutcomeDTO updateOutcome(Long id, OutcomeInput outcomeInput) throws CustomException {
        Outcome outcome = outcomeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Outcome with ID " + id + " not found"));
        return getOutcomeFromInput(outcomeInput, outcome);
    }
    public void deleteOutcome(Long id) throws CustomException {
        Outcome outcome = outcomeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Outcome with ID " + id + " not found"));
        outcomeRepository.delete(outcome);
    }
    private OutcomeDTO getOutcomeFromInput(OutcomeInput outcomeInput, Outcome outcome) throws CustomException {
        OutcomeOrder outcomeOrder = outcomeOrderRepository.findById(outcomeInput.getOutcomeOrderId())
                .orElseThrow(() -> new CustomException("OutcomeOrder with ID " + outcomeInput.getOutcomeOrderId() + " not found"));
        outcome.setOutcomeDescription(outcomeInput.getOutcomeDescription());
        outcome.setOutcomePlace(outcomeInput.getOutcomePlace());
        outcome.setOutcomePrice(outcomeInput.getOutcomePrice());
        outcome.setPaymentMethod(outcomeInput.getPaymentMethod());
        outcome.setOutcomeOrder(outcomeOrder);
        outcome = outcomeRepository.save(outcome);
        return modelMapper.map(outcome, OutcomeDTO.class);
    }
}