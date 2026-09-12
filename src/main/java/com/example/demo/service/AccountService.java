package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account updateAccount(Long id, Account account) {

        Account existingAccount = accountRepository.findById(id).orElse(null);

        if (existingAccount == null) {
            return null;
        }

        existingAccount.setCustomer(account.getCustomer());
        existingAccount.setAccountNumber(account.getAccountNumber());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setStatus(account.getStatus());

        return accountRepository.save(existingAccount);
    }

    public boolean deleteAccount(Long id) {

        if (!accountRepository.existsById(id)) {
            return false;
        }

        accountRepository.deleteById(id);
        return true;
    }
}