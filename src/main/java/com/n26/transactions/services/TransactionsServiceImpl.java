package com.n26.transactions.services;

import java.util.Map;

import com.n26.transactions.model.Transaction;

public class TransactionsServiceImpl implements TransactionsService {

    private Map<Long, Transaction> transactions;

    @Override
    public void addTransaction(Transaction transaction, long transactionId) {
        this.transactions.put(transactionId, transaction);

    }

    @Override
    public Transaction getTransaction(long transactionId) {
        return this.transactions.get(transactionId);
    }

}
