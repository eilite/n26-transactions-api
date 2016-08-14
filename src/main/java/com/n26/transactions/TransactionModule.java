package com.n26.transactions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.n26.transactions.dao.ParentTransactionDao;
import com.n26.transactions.dao.ParentTransactionDaoImpl;
import com.n26.transactions.dao.TransactionDao;
import com.n26.transactions.dao.TransactionDaoImpl;
import com.n26.transactions.services.TransactionsService;
import com.n26.transactions.services.TransactionsServiceImpl;

public class TransactionModule extends AbstractModule {

	private TransactionsService transactionsService;
	private TransactionDao transactionDao;
	private ParentTransactionDao parentTransactionDao;

	private TransactionDao provideTransactionDao() {
		if (this.transactionDao == null) {
			this.transactionDao = new TransactionDaoImpl();
		}
		return this.transactionDao;
	}

	private ParentTransactionDao provideParentDao() {
		if (this.parentTransactionDao == null) {
			this.parentTransactionDao = new ParentTransactionDaoImpl();
		}
		return this.parentTransactionDao;
	}

	@Provides
	public TransactionsService provideTransactionService() {
		if (this.transactionsService == null) {
			this.transactionsService = new TransactionsServiceImpl(this.provideTransactionDao(),
					this.provideParentDao());
		}
		return this.transactionsService;
	}

	@Override
	protected void configure() {
	}

}
