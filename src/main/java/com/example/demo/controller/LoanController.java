package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Loan;
import com.example.demo.service.LoanService;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {

        Loan savedLoan = loanService.createLoan(loan);

        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {

        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {

        Loan loan = loanService.getLoanById(id);

        if (loan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(loan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(
            @PathVariable Long id,
            @RequestBody Loan loan) {

        Loan updatedLoan = loanService.updateLoan(id, loan);

        if (updatedLoan == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedLoan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {

        boolean deleted = loanService.deleteLoan(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}