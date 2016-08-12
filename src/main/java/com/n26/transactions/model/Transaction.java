package com.n26.transactions.model;

public class Transaction {

	private long id;
	private long amount;
	private String type;
	private Long parentId;

	public Transaction(long id, long amount, String type, Long parentId) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.parentId = parentId;
	}
	
	public Transaction(long id,TransactionDTO transactionDTO){
		this(id,transactionDTO.getAmount(), transactionDTO.getType(), transactionDTO.getParentId());
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
