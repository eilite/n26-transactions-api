package com.n26.transactions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    private long amount;
    private String type;
    @JsonProperty("parent_id")
    private long parentId;

    public Transaction() {
        // jackson
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

}
