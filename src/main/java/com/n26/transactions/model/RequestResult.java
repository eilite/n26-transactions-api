package com.n26.transactions.model;

public class RequestResult {
	private String status;
	
	public RequestResult(){
	}
	
	public RequestResult(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
