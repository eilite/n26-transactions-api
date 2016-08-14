package com.n26.transactions.dao;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import com.google.common.collect.Maps;
import com.n26.transactions.model.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	private Map<Long, Transaction> transactions;

	public TransactionDaoImpl() {
		transactions = Maps.newHashMap();
	}

	@Override
	public void saveTransaction(Transaction t) {
		this.transactions.put(t.getId(), t);
	}

	@Override
	public Transaction getTransaction(long transactionId) {
		Transaction transaction = this.transactions.get(transactionId);
		if (transaction == null) {
			throw new NotFoundException("transaction not found");
		}
		return transaction;
	}

	@Override
	public Set<Long> getTransactionIdsByType(String type) {
		return this.transactions.entrySet().stream()
				.filter(entry -> entry.getValue().getType().equals(type))
				.map(entry -> entry.getKey())
				.collect(Collectors.toSet());
	}

}
