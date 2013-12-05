package com.pinecone.technology.mcommerce.learning.android.chapter07.example.test;

import java.util.Date;
import java.util.List;

import android.app.Application;

import com.pinecone.technology.mcommerce.learning.android.chapter06.example.dao.DaoMaster;
import com.pinecone.technology.mcommerce.learning.android.chapter06.example.dao.DaoSession;
import com.pinecone.technology.mcommerce.learning.android.chapter06.example.dao.Invoice;
import com.pinecone.technology.mcommerce.learning.android.chapter06.example.dao.InvoiceLine;

import de.greenrobot.dao.test.AbstractDaoSessionTest;

public class CustomerOrderTest extends
		AbstractDaoSessionTest<Application, DaoMaster, DaoSession> {

	public CustomerOrderTest() {
		super(DaoMaster.class);
	}

	public void testInvoiceToLines() {
		Date date = new Date(System.currentTimeMillis()
				- ((long) (Math.random() * 1000 * 60 * 60 * 24 * 365)));
		Invoice invoice = new Invoice(null, date, 1, null, null, null, null, null, null);
		daoSession.insert(invoice);

		addLinesToInvoice(invoice);
		addLinesToInvoice(invoice);

		List<InvoiceLine> orders = invoice.getInvoiceLines();
		assertEquals(2, orders.size());
	}

	public void testLinesToInvoice() {
		Date date = new Date(System.currentTimeMillis()
				- ((long) (Math.random() * 1000 * 60 * 60 * 24 * 365)));
		Invoice invoice = new Invoice(null, date, 1, null, null, null, null, null, null);
		daoSession.insert(invoice);

		InvoiceLine lines = addLinesToInvoice(invoice);
		Invoice invoice2 = lines.getInvoice();

		assertSame(invoice, invoice2);
	}

	public void testUpdateBirectional() {
		Date date = new Date(System.currentTimeMillis()
				- ((long) (Math.random() * 1000 * 60 * 60 * 24 * 365)));
		Invoice invoice = new Invoice(null, date, 1, null, null, null, null, null, null);
		daoSession.insert(invoice);

		addLinesToInvoice(invoice);
		List<InvoiceLine> lines = invoice.getInvoiceLines();

		InvoiceLine newInvoice = new InvoiceLine();
		newInvoice.setInvoice(invoice);
		daoSession.insert(newInvoice);
		lines.add(newInvoice);
		assertEquals(2, lines.size());

		invoice.resetInvoiceLines();
		List<InvoiceLine> lines2 = invoice.getInvoiceLines();
		assertEquals(lines.size(), lines2.size());
	}

	private InvoiceLine addLinesToInvoice(Invoice invoice) {
		InvoiceLine line = new InvoiceLine(null, invoice.getId(), 0, 0, null);
		daoSession.insert(line);
		return line;
	}

}
