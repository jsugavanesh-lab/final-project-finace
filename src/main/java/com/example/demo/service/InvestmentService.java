package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Investment;
import com.example.demo.repository.InvestmentRepository;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public Investment createInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }

    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    public Investment getInvestmentById(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }

    public Investment updateInvestment(Long id, Investment investment) {

        Investment existingInvestment =
                investmentRepository.findById(id).orElse(null);

        if (existingInvestment == null) {
            return null;
        }

        existingInvestment.setCustomer(investment.getCustomer());
        existingInvestment.setInvestmentType(investment.getInvestmentType());
        existingInvestment.setAmount(investment.getAmount());
        existingInvestment.setStartDate(investment.getStartDate());
        existingInvestment.setMaturityDate(investment.getMaturityDate());
        existingInvestment.setStatus(investment.getStatus());

        return investmentRepository.save(existingInvestment);
    }

    public boolean deleteInvestment(Long id) {

        if (!investmentRepository.existsById(id)) {
            return false;
        }

        investmentRepository.deleteById(id);
        return true;
    }
}