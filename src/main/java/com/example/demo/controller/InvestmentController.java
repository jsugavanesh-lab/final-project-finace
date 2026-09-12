package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Investment;
import com.example.demo.service.InvestmentService;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public ResponseEntity<Investment> createInvestment(
            @RequestBody Investment investment) {

        Investment savedInvestment =
                investmentService.createInvestment(investment);

        return new ResponseEntity<>(savedInvestment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Investment>> getAllInvestments() {

        return ResponseEntity.ok(
                investmentService.getAllInvestments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(
            @PathVariable Long id) {

        Investment investment =
                investmentService.getInvestmentById(id);

        if (investment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(investment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(
            @PathVariable Long id,
            @RequestBody Investment investment) {

        Investment updatedInvestment =
                investmentService.updateInvestment(id, investment);

        if (updatedInvestment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedInvestment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(
            @PathVariable Long id) {

        boolean deleted =
                investmentService.deleteInvestment(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}