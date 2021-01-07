package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class OrdersTestFail {
	private final OrderDAO orderDAO = new OrderDAO();
	private final Utils utils = new Utils();
	private final Date date = utils.getDate();

	@BeforeClass
	public static void init() {
		DBUtils.connect("rot", "pass");

	}

	@Before
	public void setup() {

		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {

		final Orders created = new Orders(4L, 3l, date, new ArrayList<>());
		assertNull(orderDAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		assertEquals(expected, orderDAO.readAll());
		
	}
	
	@Test
	public void testReadLatest() {
		assertNull(orderDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long order_id = 1L;
		assertNull(orderDAO.readOrders(order_id));
	}
	@Test
	public void testReadLines() {
		final long ID = 1L;
		assertNull(orderDAO.readLines(ID));
	}

	@Test
	public void testUpdate() {
		final Orders updated = new Orders(4L, 3l, date, new ArrayList<>());
		assertNull(orderDAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, orderDAO.delete(2));
	}
	@Test
	public void testDeleteLines() {
		assertEquals(0, orderDAO.deleteLine(2l, 3l));
	}

}
