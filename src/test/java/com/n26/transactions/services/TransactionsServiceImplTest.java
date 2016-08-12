package com.n26.transactions.services;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.n26.transactions.dao.ParentsDao;
import com.n26.transactions.dao.TransactionDao;
import com.n26.transactions.model.TransactionDTO;

public class TransactionsServiceImplTest {
	private TransactionsService fixture;
	private TransactionDao transactionDao;
	private ParentsDao parentsDao;

	@Before
	public void setUp() {
		transactionDao = mock(TransactionDao.class);
		parentsDao = mock(ParentsDao.class);
		this.fixture = new TransactionsServiceImpl(transactionDao, parentsDao);
	}

	@Test
	public void addTransaction_OK() {
		TransactionDTO transactionDTO = new TransactionDTO();
		this.fixture.addTransaction(transactionDTO, 1);

		// assertEquals(this.fixture.get)
	}

}
