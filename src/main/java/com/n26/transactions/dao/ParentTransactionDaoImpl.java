package com.n26.transactions.dao;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.n26.transactions.model.Transaction;

public class ParentTransactionDaoImpl implements ParentTransactionDao {
	private Map<Long, Set<Transaction>> parentTransactions;
	
	public ParentTransactionDaoImpl(){
		this.parentTransactions = Maps.newHashMap();
	}

	@Override
	public void addParents(Long childId, Set<Transaction> parents) {
		this.parentTransactions.put(childId, parents);
	}

	@Override
	public Set<Transaction> getParents(Long childId) {
		return this.parentTransactions.get(childId);
	}

	
}
