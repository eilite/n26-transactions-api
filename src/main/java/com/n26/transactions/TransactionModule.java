package com.n26.transactions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.n26.transactions.services.TransactionsService;
import com.n26.transactions.services.TransactionsServiceImpl;

public class TransactionModule extends AbstractModule {

    private TransactionsService transactionsService;

    @Provides
    public TransactionsService provideTransactionService() {
        if (this.transactionsService == null) {
            this.transactionsService = new TransactionsServiceImpl();
        }
        return this.transactionsService;
    }

    @Override
    protected void configure() {
        // TODO Auto-generated method stub

    }

}
