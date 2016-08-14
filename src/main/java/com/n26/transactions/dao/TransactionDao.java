package com.n26.transactions.dao;

import java.util.Set;

import com.n26.transactions.model.Transaction;

public interface TransactionDao {

	public void saveTransaction(Transaction t);

	public Transaction getTransaction(long transactionId);
	
	public Set<Long> getTransactionIdsByType(String type);
}
