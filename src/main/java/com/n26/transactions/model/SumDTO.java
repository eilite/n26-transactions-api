package com.n26.transactions.model;

public class SumDTO {
	private long sum;

	private SumDTO(long sum) {
		this.sum = sum;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

}
