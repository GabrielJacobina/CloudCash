package com.cash.payment.repository;

import com.cash.payment.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
