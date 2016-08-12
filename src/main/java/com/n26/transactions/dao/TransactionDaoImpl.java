package com.n26.transactions.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import com.n26.transactions.model.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	private Map<Long, Transaction> transactions;

	public TransactionDaoImpl() {
		transactions = new HashMap<>();
	}

	@Override
	public void saveTransaction(Transaction t) {
		this.transactions.put(t.getParentId(), t);
	}

	@Override
	public Transaction getTransaction(long transactionId) {
		Transaction transaction = this.transactions.get(transactionId);
		if (transaction == null) {
			throw new NotFoundException("transaction not found");
		}
		return transaction;
	}

}
