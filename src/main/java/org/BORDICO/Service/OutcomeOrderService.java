package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.OutcomeOrderDTO;
import org.BORDICO.Model.Entity.Outcome;
import org.BORDICO.Model.Entity.OutcomeOrder;
import org.BORDICO.Model.Inputs.OutcomeOrderInput;
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
public class OutcomeOrderService {
    private final OutcomeOrderRepository outcomeOrderRepository;
    private final OutcomeRepository outcomeRepository;
    private final ModelMapper modelMapper;
    public OutcomeOrderDTO createOutcomeOrder(OutcomeOrderInput outcomeOrderInput) throws CustomException {
        OutcomeOrder outcomeOrder = new OutcomeOrder();
        return getOutcomeOrderFromInput(outcomeOrderInput, outcomeOrder);
    }
    public OutcomeOrderDTO getOutcomeOrderById(Long id) throws CustomException {
        OutcomeOrder outcomeOrder = outcomeOrderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Outcome Order with ID " + id + " not found"));
        return modelMapper.map(outcomeOrder, OutcomeOrderDTO.class);
    }
    public PageOutput<OutcomeOrderDTO> getAllOutcomeOrders(PageInput pageInput) {
        Page<OutcomeOrder> outcomeOrders = outcomeOrderRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                outcomeOrders.getNumber(),
                outcomeOrders.getTotalPages(),
                outcomeOrders.getTotalElements(),
                outcomeOrders.getContent().stream()
                        .map(outcomeOrder -> modelMapper.map(outcomeOrder, OutcomeOrderDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public OutcomeOrderDTO updateOutcomeOrder(Long id, OutcomeOrderInput outcomeOrderInput) throws CustomException {
        OutcomeOrder outcomeOrder = outcomeOrderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Outcome Order with ID " + id + " not found"));
        return getOutcomeOrderFromInput(outcomeOrderInput, outcomeOrder);
    }
    public void deleteOutcomeOrder(Long id) throws CustomException {
        OutcomeOrder outcomeOrder = outcomeOrderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Outcome Order with ID " + id + " not found"));
        outcomeOrderRepository.delete(outcomeOrder);
    }
    private OutcomeOrderDTO getOutcomeOrderFromInput(OutcomeOrderInput outcomeOrderInput, OutcomeOrder outcomeOrder) throws CustomException {
        Outcome outcome = outcomeRepository.findById(outcomeOrderInput.getOutcomeId())
                .orElseThrow(() -> new CustomException("Outcome with ID " + outcomeOrderInput.getOutcomeId() + " not found"));
        outcomeOrder.setOrderPlace(outcomeOrderInput.getOrderPlace());
        outcomeOrder.setOrderPrice(outcomeOrderInput.getOrderPrice());
        outcomeOrder.setPaymentMethod(outcomeOrderInput.getPaymentMethod());
        outcomeOrder.setOutcome(outcome);
        outcomeOrder = outcomeOrderRepository.save(outcomeOrder);
        return modelMapper.map(outcomeOrder, OutcomeOrderDTO.class);
    }
}