package com.n26.transactions.dao;

import java.util.Set;

public interface ParentsDao {
	
	public void addChild(long parentId, long transactionId);
	
	public Set<Long> getChildrenIds(long parentId);
}
