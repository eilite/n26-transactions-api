package com.n26.transactions.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.n26.transactions.model.RequestResult;
import com.n26.transactions.model.SumDTO;
import com.n26.transactions.model.Transaction;
import com.n26.transactions.model.TransactionDTO;
import com.n26.transactions.services.TransactionsService;

@Path("/transactionservice")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {
	private TransactionsService transactionsService;

	@Inject
	public TransactionResource(TransactionsService transactionsService) {
		this.transactionsService = transactionsService;
	}

	@PUT
	@Path("/transaction/{transactionId}")
	public Response addTransaction(@PathParam(value = "transactionId") long transactionId,
			TransactionDTO transactionDTO) {
		this.transactionsService.addTransaction(transactionDTO, transactionId);
		return Response.ok(new RequestResult("ok")).build();
	}

	@GET
	@Path("/transaction/{transactionId}")
	public Response getTransaction(@PathParam(value = "transactionId") long transactionId) {
		Transaction transaction = this.transactionsService.getTransaction(transactionId);
		return Response.ok(transaction).build();
	}

	@GET
	@Path("/types/{type}")
	public Response getTransactionsByType(@PathParam(value = "type") String type) {
		Set<Long> transactionIds = this.transactionsService.getTransactionsByType(type);
		return Response.ok(transactionIds).build();
	}

	@GET
	@Path("/sum/{transactionId}")
	public Response getTransactionsSum(@PathParam(value = "transactionId") long transactionId) {
		SumDTO sum = this.transactionsService.getTransactionsSum(transactionId);
		return Response.ok(sum).build();
	}
}
