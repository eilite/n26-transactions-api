package com.n26.transactions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.n26.transactions.dao.ParentsDao;
import com.n26.transactions.dao.ParentsDaoImpl;
import com.n26.transactions.dao.TransactionDao;
import com.n26.transactions.dao.TransactionDaoImpl;
import com.n26.transactions.services.TransactionsService;
import com.n26.transactions.services.TransactionsServiceImpl;

public class TransactionModule extends AbstractModule {

	private TransactionsService transactionsService;
	private TransactionDao transactionDao;
	private ParentsDao parentsDao;

	private TransactionDao provideTransactionDao() {
		if (this.transactionDao == null) {
			this.transactionDao = new TransactionDaoImpl();
		}
		return this.transactionDao;
	}
	
	private ParentsDao provideParentsDao() {
		if (this.parentsDao == null) {
			this.parentsDao = new ParentsDaoImpl();
		}
		return this.parentsDao;
	}

	@Provides
	public TransactionsService provideTransactionService() {
		if (this.transactionsService == null) {
			this.transactionsService = new TransactionsServiceImpl(this.provideTransactionDao(), this.provideParentsDao());
		}
		return this.transactionsService;
	}

	@Override
	protected void configure() {
	}

}
