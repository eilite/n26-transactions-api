package com.n26.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDTO {

	private long amount;
	private String type;
	@JsonProperty("parent_id")
	private Long parentId;

	public TransactionDTO() {
	}

	public TransactionDTO(Transaction transaction) {
		this.amount = transaction.getAmount();
		this.type = transaction.getType();
		this.parentId = transaction.getParentId();
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
