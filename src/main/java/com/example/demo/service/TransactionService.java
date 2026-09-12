package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {

        Transaction existingTransaction =
                transactionRepository.findById(id).orElse(null);

        if (existingTransaction == null) {
            return null;
        }

        existingTransaction.setAccount(transaction.getAccount());
        existingTransaction.setTransactionType(transaction.getTransactionType());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setTransactionDate(transaction.getTransactionDate());
        existingTransaction.setDescription(transaction.getDescription());

        return transactionRepository.save(existingTransaction);
    }

    public boolean deleteTransaction(Long id) {

        if (!transactionRepository.existsById(id)) {
            return false;
        }

        transactionRepository.deleteById(id);
        return true;
    }
}