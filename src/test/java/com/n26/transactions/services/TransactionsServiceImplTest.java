package com.n26.transactions.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.n26.transactions.dao.ParentTransactionDao;
import com.n26.transactions.dao.TransactionDao;
import com.n26.transactions.model.Transaction;
import com.n26.transactions.model.TransactionDTO;

import javassist.NotFoundException;

public class TransactionsServiceImplTest {
	private TransactionsServiceImpl fixture;
	private TransactionDao transactionDao;
	private ParentTransactionDao parentTransactionDao;

	@Before
	public void setUp() {
		transactionDao = mock(TransactionDao.class);
		parentTransactionDao = mock(ParentTransactionDao.class);
		this.fixture = new TransactionsServiceImpl(transactionDao, parentTransactionDao);
	}

	// test transactions adding
	@Test
	public void addTransaction_OK_NO_PARENT_ID() {
		TransactionDTO transactionDTO = new TransactionDTO();
		this.fixture.addTransaction(transactionDTO, 1);

		verify(this.transactionDao).saveTransaction(any(Transaction.class));
	}

	@Test
	public void addTransaction_OK_PARENT_ID() {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setParentId(2L);

		Transaction parent = new Transaction();
		parent.setId(2L);

		when(this.transactionDao.getTransaction(2L)).thenReturn(parent);

		this.fixture.addTransaction(transactionDTO, 1);

		verify(this.parentTransactionDao).addParents(1L, Sets.newHashSet(parent));
	}

	// test the parent transactions saving
	@Test
	public void addParentTransaction_NO_PARENTS_TRANSACTIONS() {
		long transactionId = 2;
		long parentTransactionId = 1;

		Transaction parent = new Transaction();

		when(transactionDao.getTransaction(parentTransactionId)).thenReturn(parent);
		when(parentTransactionDao.getParents(transactionId)).thenReturn(null);

		fixture.saveParent(transactionId, parentTransactionId);
		// fixture.

		verify(parentTransactionDao).addParents(transactionId, Sets.newHashSet(parent));
	}

	@Test
	public void addParentTransaction_PARENTS_TRANSACTIONS() {
		long transactionId = 2;
		long parentTransactionId = 1;

		Transaction parent = new Transaction();
		parent.setId(parentTransactionId);

		Transaction grandParent = new Transaction();

		when(transactionDao.getTransaction(parentTransactionId)).thenReturn(parent);
		when(parentTransactionDao.getParents(parentTransactionId)).thenReturn(Sets.newHashSet(grandParent));

		fixture.saveParent(transactionId, parentTransactionId);

		verify(parentTransactionDao).addParents(transactionId, Sets.newHashSet(parent, grandParent));
	}

	// test get transaction method
	@SuppressWarnings("unchecked")
	@Test(expected = NotFoundException.class)
	public void getTransaction_NOK() {
		long transactionId = 2;

		when(transactionDao.getTransaction(transactionId)).thenThrow(NotFoundException.class);

		fixture.getTransaction(transactionId);
	}

	@Test
	public void getTransaction_OK() {
		long transactionId = 2;
		Transaction t = new Transaction(transactionId, 100, "car", 1L);
		TransactionDTO transactionDTO = new TransactionDTO(t);

		when(transactionDao.getTransaction(transactionId)).thenReturn(t);

		assertEquals(transactionDTO, fixture.getTransaction(transactionId));
	}

	// test transactions sum
	@Test
	public void getTransactionSum_PARENTS() {
		long transactionId = 1;
		Transaction transaction = new Transaction();
		transaction.setId(transactionId);
		transaction.setAmount(1);

		Transaction parent1 = new Transaction();
		parent1.setAmount(2);

		Transaction parent2 = new Transaction();
		parent2.setAmount(3);

		when(transactionDao.getTransaction(transactionId)).thenReturn(transaction);
		when(parentTransactionDao.getParents(transactionId)).thenReturn(Sets.newHashSet(parent1, parent2));

		assertEquals(transaction.getAmount() + parent1.getAmount() + parent2.getAmount(),
				fixture.getTransactionsSum(transactionId).getSum(), 0);

	}

	@Test
	public void getTransactionSum_NO_PARENT() {
		long transactionId = 1;
		Transaction transaction = new Transaction();
		transaction.setId(transactionId);
		transaction.setAmount(1);

		when(transactionDao.getTransaction(transactionId)).thenReturn(transaction);
		when(parentTransactionDao.getParents(transactionId)).thenReturn(null);

		assertEquals(transaction.getAmount(), fixture.getTransactionsSum(transactionId).getSum(), 0);

	}
}
