package com.sp.uc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.uc.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountIdOrderByInitiationDate(long id);
}
