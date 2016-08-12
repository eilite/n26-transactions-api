package com.n26.transactions.services;

import com.n26.transactions.model.Transaction;

public interface TransactionsService {

    public void addTransaction(Transaction transaction, long transactionId);

    public Transaction getTransaction(long transactionId);
}
