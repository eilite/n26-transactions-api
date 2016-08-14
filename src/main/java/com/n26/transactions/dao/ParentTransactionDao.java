package com.n26.transactions.dao;

import java.util.Set;

import com.n26.transactions.model.Transaction;

public interface ParentTransactionDao {

	public void addParents(Long childId, Set<Transaction> parents);

	public Set<Transaction> getParents(Long childId);

}
