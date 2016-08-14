package com.n26.transactions.services;

import java.util.Set;

import com.n26.transactions.model.SumDTO;
import com.n26.transactions.model.TransactionDTO;

public interface TransactionsService {

	public void addTransaction(TransactionDTO transactionDTO, long transactionId);

	public TransactionDTO getTransaction(long transactionId);

	public Set<Long> getTransactionsByType(String type);
	
	public SumDTO getTransactionsSum(long transactionId);
}
