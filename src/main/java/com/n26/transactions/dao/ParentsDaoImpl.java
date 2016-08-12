package com.n26.transactions.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Sets;

public class ParentsDaoImpl implements ParentsDao {

	private Map<Long, Set<Long>> parents;

	public ParentsDaoImpl() {
		this.parents = new HashMap<>();
	}

	@Override
	public void addChild(long parentId, long transactionId) {
		Set<Long> childrenIds = this.getChildrenIds(parentId);
		if (CollectionUtils.isEmpty(childrenIds)) {
			this.parents.put(parentId, Sets.newHashSet(transactionId));
		} else {
			childrenIds.add(transactionId);
			this.parents.put(parentId, Sets.newHashSet(childrenIds));
		}

	}

	@Override
	public Set<Long> getChildrenIds(long parentId) {
		return this.parents.get(parentId);
	}

}
