package com.n26.transactions.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.n26.transactions.dao.ParentsDao;
import com.n26.transactions.dao.TransactionDao;
import com.n26.transactions.model.SumDTO;
import com.n26.transactions.model.Transaction;
import com.n26.transactions.model.TransactionDTO;

public class TransactionsServiceImpl implements TransactionsService {
	private final TransactionDao transactionDao;
	private final ParentsDao parentsDao;
	private Map<Long, Transaction> transactions;

	public TransactionsServiceImpl(TransactionDao transactionDao, ParentsDao parentsDao) {
		this.transactionDao = transactionDao;
		this.parentsDao = parentsDao;
		this.transactions = new HashMap<>();
	}

	@Override
	public void addTransaction(TransactionDTO transactionDTO, long transactionId) {
		Transaction transaction = new Transaction(transactionId, transactionDTO);
		this.transactionDao.saveTransaction(transaction);
		if (transaction.getParentId() != null) {
			this.parentsDao.addChild(transaction.getParentId(), transactionId);
		}
	}

	@Override
	public Transaction getTransaction(long transactionId) {
		return this.transactionDao.getTransaction(transactionId);
	}

	@Override
	public Set<Long> getTransactionsByType(String type) {
		return this.transactions.values().stream().filter(transaction -> transaction.getType().equals(type))
				.map(transaction -> transaction.getId()).collect(Collectors.toSet());
	}

	@Override
	public SumDTO getTransactionsSum(long transactionId) {
		// recursive stuff
		return null;
	}

}
