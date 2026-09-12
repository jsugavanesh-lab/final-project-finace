package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan updateLoan(Long id, Loan loan) {

        Loan existingLoan = loanRepository.findById(id).orElse(null);

        if (existingLoan == null) {
            return null;
        }

        existingLoan.setCustomer(loan.getCustomer());
        existingLoan.setLoanType(loan.getLoanType());
        existingLoan.setPrincipalAmount(loan.getPrincipalAmount());
        existingLoan.setInterestRate(loan.getInterestRate());
        existingLoan.setStartDate(loan.getStartDate());
        existingLoan.setEndDate(loan.getEndDate());
        existingLoan.setStatus(loan.getStatus());

        return loanRepository.save(existingLoan);
    }

    public boolean deleteLoan(Long id) {

        if (!loanRepository.existsById(id)) {
            return false;
        }

        loanRepository.deleteById(id);
        return true;
    }
}