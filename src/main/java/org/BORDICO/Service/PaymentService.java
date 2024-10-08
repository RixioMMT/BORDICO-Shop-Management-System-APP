package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.PaymentDTO;
import org.BORDICO.Model.Entity.Cart;
import org.BORDICO.Model.Entity.Payment;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.PaymentInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.CartRepository;
import org.BORDICO.Repository.PaymentRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final CartRepository cartRepository;
    public PaymentDTO createPayment(PaymentInput paymentInput) throws CustomException {
        Payment payment = new Payment();
        return getPaymentFromInput(paymentInput, payment);
    }
    public PageOutput<PaymentDTO> getAllPayments(PageInput pageInput) {
        Page<Payment> payments = paymentRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                payments.getNumber(),
                payments.getTotalPages(),
                payments.getTotalElements(),
                payments.getContent().stream()
                        .map(payment -> modelMapperUtil.map(payment, PaymentDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public PaymentDTO getPaymentById(Long id) throws CustomException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Payment with ID " + id + " not found"));
        return modelMapper.map(payment, PaymentDTO.class);
    }
    public PaymentDTO updatePayment(Long id, PaymentInput paymentInput) throws CustomException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Payment with ID " + id + " not found"));
        return getPaymentFromInput(paymentInput, payment);
    }
    public void deletePayment(Long id) throws CustomException {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Payment with ID " + id + " not found"));
        paymentRepository.delete(payment);
    }
    private PaymentDTO getPaymentFromInput(PaymentInput paymentInput, Payment payment) throws CustomException {
        Cart cart = cartRepository.findById(paymentInput.getCartId())
                .orElseThrow(() -> new CustomException("Cart with ID " + paymentInput.getCartId() + " not found"));
        payment.setClientName(paymentInput.getClientName());
        payment.setBank(paymentInput.getBank());
        payment.setConfirmationNumber(paymentInput.getConfirmationNumber());
        payment.setPaymentPrice(paymentInput.getPaymentPrice());
        payment.setPaymentMethod(paymentInput.getPaymentMethod());
        payment.setPaymentStatus(paymentInput.getPaymentStatus());
        payment.setCart(cart);
        payment = paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }
}