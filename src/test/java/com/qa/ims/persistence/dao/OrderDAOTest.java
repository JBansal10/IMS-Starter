package com.qa.ims.persistence.dao;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class OrderDAOTest {

	private Items acValhalla = new Items(1L, "Valhalla", 476L, 19.62D);
	private Items wdLegion = new Items(2L, "Legion", 356L, 8.99D);
	private Items pFunny = new Items(3L, "Piers Humour", 678L, 0.01D);
	private final OrderDAO DAO = new OrderDAO();
	private final ItemsDAO iDAO = new ItemsDAO();
	private final Utils utils = new Utils();
	private final Date date = utils.getDate();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {

		List<Items> items = new ArrayList<Items>();
		final Orders created = new Orders(1L, date, items);
		final Orders expected = new Orders(3L, 1L, date, items);
		assertEquals(expected, DAO.create(created));
	}

	@Test
	public void testCreateLine() {
		long order_id = 1L;
		long item_id = 2L;

		Items itemIterated = new Items(2L, "Legion", 355L, 8.99D);
		DAO.createLine(order_id, item_id);
		assertEquals(itemIterated, iDAO.readItems(item_id));
	}

	@Test
	public void testReadAll() {
		List<Items> items1 = new ArrayList<Items>();
		List<Items> items2 = new ArrayList<Items>();
		List<Orders> expected = new ArrayList<Orders>();
		final Orders order1 = new Orders(1L, 1L, date, items1);
		final Orders order2 = new Orders(2L, 1L, date, items2);
		order1.getItems().add(wdLegion);
		order1.getItems().add(pFunny);
		order1.getItems().add(acValhalla);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);
		order2.getItems().add(wdLegion);

		expected.add(order1);
		expected.add(order2);

		for (Orders order : expected) {
			for (Items item : order.getItems()) {
				item.setStock(1L);
			}
		}

		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		List<Items> items1 = new ArrayList<Items>();
		items1.add(wdLegion);
		items1.add(wdLegion);
		items1.add(wdLegion);
		items1.add(wdLegion);
		items1.add(wdLegion);
		items1.add(wdLegion);
		items1.add(wdLegion);
		items1.add(wdLegion);
		final Orders expected = new Orders(2L, 1L, date, items1);
		
		for (Items items : expected.getItems()) {
			items.setStock(1L);
		}
		
		assertEquals(expected, DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long order_id = 1L;
		List<Items> items = new ArrayList<>();
		final Orders orders = new Orders(1L, 1L, date, items);
		
		orders.getItems().add(wdLegion);
		orders.getItems().add(pFunny);
		orders.getItems().add(acValhalla);
		
		for (Items item : orders.getItems()) {
			item.setStock(1L);
		}
		
		assertEquals(orders, DAO.readOrders(order_id));

	}
	
	@Test
	public void testReadLine() {
		final long order_id = 1L;
		List<Items> items = new ArrayList<>();
		final Orders orders = new Orders(1L, 1L, date, items);
		
		orders.getItems().add(wdLegion);
		orders.getItems().add(pFunny);
		orders.getItems().add(acValhalla);
		
		assertEquals(items, DAO.readLines(order_id));
	}
	
	@Test
	public void testUpdate() {
		List<Items> items = new ArrayList<>();
		final Orders updated = new Orders(1L, 1L, date, items);
		
		updated.getItems().add(wdLegion);
		updated.getItems().add(pFunny);
		updated.getItems().add(acValhalla);
		
		for(Items item : updated.getItems()) {
			item.setStock(1L);
		}
		assertEquals(updated, DAO.update(updated));
		
	}
	
	@Test
	public void testDelete() {
		List<Items> items = new ArrayList<>();
		final Orders updated = new Orders(1L, 1L, date, items);
		DAO.create(updated);
		assertEquals(1, DAO.delete(1L));
	}
	
	@Test
	public void testDeleteLine() {
		final long order_id = 2L;
		final long item_id = 3L;
		assertEquals(8, DAO.deleteLine(order_id, item_id));
	}

}
