package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.ShippingDTO;
import org.BORDICO.Model.Entity.IncomeOrder;
import org.BORDICO.Model.Entity.Shipping;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Inputs.ShippingInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.IncomeOrderRepository;
import org.BORDICO.Repository.ShippingRepository;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final ShippingRepository shippingRepository;
    private final IncomeOrderRepository incomeOrderRepository;
    private final ModelMapper modelMapper;
    public ShippingDTO createShipping(ShippingInput shippingInput) throws CustomException {
        Shipping shipping = new Shipping();
        return getShippingFromInput(shippingInput, shipping);
    }
    public PageOutput<ShippingDTO> getAllShippings(PageInput pageInput) {
        Page<Shipping> shippings = shippingRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                shippings.getNumber(),
                shippings.getTotalPages(),
                shippings.getTotalElements(),
                shippings.getContent().stream()
                        .map(shipping -> modelMapper.map(shipping, ShippingDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public ShippingDTO getShippingById(Long id) throws CustomException {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Shipping with ID " + id + " not found"));
        return modelMapper.map(shipping, ShippingDTO.class);
    }
    public ShippingDTO updateShipping(Long id, ShippingInput shippingInput) throws CustomException {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Shipping with ID " + id + " not found"));
        return getShippingFromInput(shippingInput, shipping);
    }
    public void deleteShipping(Long id) throws CustomException {
        Shipping shipping = shippingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Shipping with ID " + id + " not found"));
        shippingRepository.delete(shipping);
    }
    private ShippingDTO getShippingFromInput(ShippingInput shippingInput, Shipping shipping) throws CustomException {
        IncomeOrder incomeOrder = incomeOrderRepository.findById(shippingInput.getIncomeOrder())
                .orElseThrow(() -> new CustomException("IncomeOrder with ID " + shippingInput.getIncomeOrder() + " not found"));
        shipping.setCarrier(shippingInput.getCarrier());
        shipping.setTrackingNumber(shippingInput.getTrackingNumber());
        shipping.setShippingDate(shippingInput.getShippingDate());
        shipping.setShippingStatus(shippingInput.getShippingStatus());
        shipping.setIncomeOrder(incomeOrder);
        shipping = shippingRepository.save(shipping);
        return modelMapper.map(shipping, ShippingDTO.class);
    }
}