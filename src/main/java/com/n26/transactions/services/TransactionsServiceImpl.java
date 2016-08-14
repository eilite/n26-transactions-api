package com.n26.transactions.services;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.n26.transactions.dao.ParentTransactionDao;
import com.n26.transactions.dao.TransactionDao;
import com.n26.transactions.model.SumDTO;
import com.n26.transactions.model.Transaction;
import com.n26.transactions.model.TransactionDTO;

import jersey.repackaged.com.google.common.collect.Sets;

public class TransactionsServiceImpl implements TransactionsService {
	private final TransactionDao transactionDao;
	private final ParentTransactionDao parentTransactionDao;

	public TransactionsServiceImpl(TransactionDao transactionDao, ParentTransactionDao parentTransactionDao) {
		this.transactionDao = transactionDao;
		this.parentTransactionDao = parentTransactionDao;
	}

	@Override
	public void addTransaction(TransactionDTO transactionDTO, long transactionId) {
		Transaction transaction = new Transaction(transactionId, transactionDTO);
		if (transactionDTO.getParentId() != null) {
			this.saveParent(transactionId, transaction.getParentId());
		}
		this.transactionDao.saveTransaction(transaction);
	}

	/**
	 * save the transaction and the parents transactions transitively
	 * 
	 * @param childId
	 *            transaction Id
	 * @param parentId
	 *            transaction parent Id
	 */
	protected void saveParent(long childId, Long parentId) {
		Transaction parent = this.transactionDao.getTransaction(parentId);
		Set<Transaction> parents = Sets.newHashSet(parent);
		Set<Transaction> grandParents = this.parentTransactionDao.getParents(parent.getId());

		if (CollectionUtils.isNotEmpty(grandParents)) {
			parents.addAll(grandParents);
		}
		this.parentTransactionDao.addParents(childId, parents);
	}

	@Override
	public TransactionDTO getTransaction(long transactionId) {
		Transaction transaction = this.transactionDao.getTransaction(transactionId);
		return new TransactionDTO(transaction);
	}

	@Override
	public Set<Long> getTransactionsByType(String type) {
		return this.transactionDao.getTransactionIdsByType(type);
	}

	@Override
	public SumDTO getTransactionsSum(long transactionId) {
		Transaction transaction = this.transactionDao.getTransaction(transactionId);
		Double sum = transaction.getAmount();

		Set<Transaction> parents = this.parentTransactionDao.getParents(transactionId);
		if (CollectionUtils.isNotEmpty(parents)) {
			sum += parents.stream().map(parent -> parent.getAmount()).reduce(0D, (a, b) -> a + b);
		}

		return new SumDTO(sum);
	}

}
