package com.n26.transactions.services;

import java.util.Set;

import com.n26.transactions.model.SumDTO;
import com.n26.transactions.model.Transaction;
import com.n26.transactions.model.TransactionDTO;

public interface TransactionsService {

	public void addTransaction(TransactionDTO transaction, long transactionId);

	public Transaction getTransaction(long transactionId);

	public Set<Long> getTransactionsByType(String type);
	
	public SumDTO getTransactionsSum(long transactionId);
}
