package com.sp.uc.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.uc.entity.Account;
import com.sp.uc.entity.ResponseTransaction;
import com.sp.uc.entity.Transaction;
import com.sp.uc.repository.AccountRepository;
import com.sp.uc.repository.TransactionRepository;
import com.sp.uc.repository.utils.TransactionInput;

@Service
public class TransactionService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseTransaction makeTransfer(TransactionInput transactionInput) {
        // TODO refactor synchronous implementation with messaging queue
        String sourceSortCode = transactionInput.getSourceAccount().getSortCode();
        String sourceAccountNumber = transactionInput.getSourceAccount().getAccountNumber();
        Optional<Account> sourceAccount = accountRepository
                .findBySortCodeAndAccountNumber(sourceSortCode, sourceAccountNumber);

        String targetSortCode = transactionInput.getTargetAccount().getSortCode();
        String targetAccountNumber = transactionInput.getTargetAccount().getAccountNumber();
        Optional<Account> targetAccount = accountRepository
                .findBySortCodeAndAccountNumber(targetSortCode, targetAccountNumber);

        if (sourceSortCode!=null && sourceAccountNumber!=null) {
            if (isAmountAvailable(transactionInput.getAmount(), sourceAccount.get().getCurrentBalance())) {
            	Transaction transaction = new Transaction();
            	ResponseTransaction responseTransaction = new ResponseTransaction();

                transaction.setAmount(transactionInput.getAmount());
                transaction.setSourceAccountId(sourceAccount.get().getId());
                transaction.setTargetAccountId(targetAccount.get().getId());
                transaction.setTargetOwnerName(targetAccount.get().getOwnerName());
                transaction.setInitiationDate(LocalDateTime.now());
                transaction.setCompletionDate(LocalDateTime.now());
                transaction.setReference(transactionInput.getReference());
              
                updateAccountBalance(sourceAccount.get(), transactionInput.getAmount());
                Transaction transaction2 = transactionRepository.save(transaction);
                responseTransaction.setAmount(transaction2.getAmount());
                responseTransaction.setReference(transaction2.getReference());
                responseTransaction.setSourceAccountId(transaction2.getTargetAccountId());
                responseTransaction.setTargetOwnerName(transaction2.getTargetOwnerName());
                return responseTransaction;
            }
        }
        return null;
    }

    private void updateAccountBalance(Account account, double amount) {
        account.setCurrentBalance((account.getCurrentBalance() - amount));
        accountRepository.save(account);
    }

    // TODO support overdrafts or credit account
    private boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }


}
