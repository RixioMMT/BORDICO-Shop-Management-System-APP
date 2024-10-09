package org.BORDICO.Service;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Exceptions.CustomException;
import org.BORDICO.Model.DTO.IncomeDTO;
import org.BORDICO.Model.Entity.Income;
import org.BORDICO.Model.Entity.IncomeOrder;
import org.BORDICO.Model.Inputs.IncomeInput;
import org.BORDICO.Model.Inputs.PageInput;
import org.BORDICO.Model.Pagination.PageOutput;
import org.BORDICO.Repository.IncomeOrderRepository;
import org.BORDICO.Repository.IncomeRepository;
import org.BORDICO.Util.ModelMapperUtil;
import org.BORDICO.Util.PageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtil modelMapperUtil;
    private final IncomeOrderRepository incomeOrderRepository;
    public IncomeDTO createIncome(IncomeInput incomeInput) throws CustomException {
        Income income = new Income();
        return getIncomeFromInput(incomeInput, income);
    }
    public PageOutput<IncomeDTO> getAllIncomes(PageInput pageInput) {
        Page<Income> incomes = incomeRepository.findAll(PageUtil.buildPage(pageInput));
        return new PageOutput<>(
                incomes.getNumber(),
                incomes.getTotalPages(),
                incomes.getTotalElements(),
                incomes.getContent().stream()
                        .map(income -> modelMapperUtil.map(income, IncomeDTO.class))
                        .collect(Collectors.toSet())
        );
    }
    public IncomeDTO getIncomeById(Long id) throws CustomException {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Income with ID " + id + " not found"));
        return modelMapper.map(income, IncomeDTO.class);
    }
    public IncomeDTO updateIncome(Long id, IncomeInput incomeInput) throws CustomException {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Income with ID " + id + " not found"));
        return getIncomeFromInput(incomeInput, income);
    }
    public void deleteIncome(Long id) throws CustomException {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Income with ID " + id + " not found"));
        incomeRepository.delete(income);
    }
    private IncomeDTO getIncomeFromInput(IncomeInput incomeInput, Income income) throws CustomException {
        IncomeOrder incomeOrder = incomeOrderRepository.findById(incomeInput.getIncomeOrderId())
                .orElseThrow(() -> new CustomException("IncomeOrder with ID " + incomeInput.getIncomeOrderId() + " not found"));
        income.setIncomeDescription(incomeInput.getIncomeDescription());
        income.setIncomePrice(incomeInput.getIncomePrice());
        income.setIncomePlatform(incomeInput.getIncomePlatform());
        income.setPaymentMethod(incomeInput.getPaymentMethod());
        income.setIncomeOrder(incomeOrder);
        income = incomeRepository.save(income);
        return modelMapper.map(income, IncomeDTO.class);
    }
}