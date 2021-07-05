package com.sp.uc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.uc.entity.Account;
import com.sp.uc.repository.AccountRepository;
import com.sp.uc.repository.TransactionRepository;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account getAccount(String sortCode, String accountNumber) {
        Optional<Account> account = accountRepository
                .findBySortCodeAndAccountNumber(sortCode, accountNumber);

        account.ifPresent(value ->
                value.setTransactions(transactionRepository
                        .findBySourceAccountIdOrderByInitiationDate(value.getId())));

        return account.orElse(null);
    }
}
