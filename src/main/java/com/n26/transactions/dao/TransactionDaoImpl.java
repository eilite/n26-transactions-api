package com.n26.transactions.dao;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.google.common.collect.Maps;
import com.n26.transactions.model.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	private Map<Long, Transaction> transactions;

	public TransactionDaoImpl() {
		transactions = Maps.newHashMap();
	}

	@Override
	public void saveTransaction(Transaction t) {
		if (this.transactions.containsKey(t.getId())) {
			throw new WebApplicationException("transaction already exists", Status.CONFLICT);
		}
		this.transactions.put(t.getId(), t);
	}

	@Override
	public Transaction getTransaction(long transactionId) {
		if (!this.transactions.containsKey(transactionId)) {
			throw new NotFoundException("transaction not found");
		}
		return this.transactions.get(transactionId);
	}

	@Override
	public Set<Long> getTransactionIdsByType(String type) {
		return this.transactions.entrySet().stream()
				.filter(entry -> entry.getValue().getType().equals(type))
				.map(entry -> entry.getKey())
				.collect(Collectors.toSet());
	}

}
