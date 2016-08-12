package com.n26.transactions.resource;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.n26.transactions.model.Transaction;
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
    @Path("/transaction")
    public Response addTransaction(@PathParam(value = "transactionId") long transactionId, Transaction transaction) {
        try {
            this.transactionsService.addTransaction(transaction, transactionId);
            return Response.status(Status.OK).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
