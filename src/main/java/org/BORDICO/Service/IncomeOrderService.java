package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.IncomeOrderDTO;
import org.BORDICO.Model.Entity.IncomeOrder;
import org.BORDICO.Model.Entity.Payment;
import org.BORDICO.Model.Enum.PaymentStatus;
import org.BORDICO.Model.Inputs.IncomeOrderInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.IncomeOrderRepository;
import org.BORDICO.Repository.PaymentRepository;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeOrderService {
    private final IncomeOrderRepository incomeOrderRepository;
    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;
    public IncomeOrderDTO createIncomeOrder(IncomeOrderInput incomeOrderInput) throws CustomException {
        IncomeOrder incomeOrder = new IncomeOrder();
        return getIncomeOrderFromInput(incomeOrderInput, incomeOrder);
    }
    public PageOutput<IncomeOrderDTO> getAllIncomeOrders(PageInput pageInput) {
        Page<IncomeOrder> incomeOrders = incomeOrderRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                incomeOrders.getNumber(),
                incomeOrders.getTotalPages(),
                incomeOrders.getTotalElements(),
                incomeOrders.getContent().stream()
                        .map(order -> modelMapper.map(order, IncomeOrderDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public IncomeOrderDTO getIncomeOrderById(Long id) throws CustomException {
        IncomeOrder incomeOrder = incomeOrderRepository.findById(id)
                .orElseThrow(() -> new CustomException("IncomeOrder with ID " + id + " not found"));
        return modelMapper.map(incomeOrder, IncomeOrderDTO.class);
    }
    public IncomeOrderDTO updateIncomeOrder(Long id, IncomeOrderInput incomeOrderInput) throws CustomException {
        IncomeOrder incomeOrder = incomeOrderRepository.findById(id)
                .orElseThrow(() -> new CustomException("IncomeOrder with ID " + id + " not found"));
        return getIncomeOrderFromInput(incomeOrderInput, incomeOrder);
    }
    public void deleteIncomeOrder(Long id) throws CustomException {
        IncomeOrder incomeOrder = incomeOrderRepository.findById(id)
                .orElseThrow(() -> new CustomException("IncomeOrder with ID " + id + " not found"));
        incomeOrderRepository.delete(incomeOrder);
    }
    private IncomeOrderDTO getIncomeOrderFromInput(IncomeOrderInput incomeOrderInput, IncomeOrder incomeOrder) throws CustomException {
        Payment payment = paymentRepository.findById(incomeOrderInput.getPaymentId())
                .orElseThrow(() -> new CustomException("Payment with ID " + incomeOrderInput.getPaymentId() + " not found"));
        if (!payment.getPaymentStatus().equals(PaymentStatus.VERIFIED)) {
            throw new CustomException("Payment with ID " + incomeOrderInput.getPaymentId() + " has not been verified");
        }
        incomeOrder.setPayment(payment);
        incomeOrder.setClientName(incomeOrderInput.getClientName());
        incomeOrder.setShippingAddress(incomeOrderInput.getShippingAddress());
        incomeOrder.setOrderPrice(incomeOrderInput.getOrderPrice());
        incomeOrder.setPaymentMethod(incomeOrderInput.getPaymentMethod());
        incomeOrder.setIncomePlatform(incomeOrderInput.getIncomePlatform());
        incomeOrder = incomeOrderRepository.save(incomeOrder);
        return modelMapper.map(incomeOrder, IncomeOrderDTO.class);
    }
}