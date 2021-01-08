package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemsDAO itemsDAO;

	@Mock
	private CustomerDAO customerDAO;

	@Mock
	private OrderDAO ordersDAO;

	@InjectMocks
	private OrdersController ordersController;

	@Test
	public void testCreate() {
		Utils util = new Utils();
		final Date date = util.getDate();
		List<Customer> customers = new ArrayList<>();
		List<Items> items = new ArrayList<>();
		Orders expected = new Orders(1L, 1L, date, items);

		long id = 1L;
		long item_id = 2L;

		Items acValhalla = new Items(1L, "Valhalla", 476L, 19.62D);
		Items wdLegion = new Items(2L, "Legion", 356L, 8.99D);
		Items pFunny = new Items(3L, "Piers Humour", 678L, 0.01D);

		expected.getItems().add(wdLegion);
		expected.getItems().add(acValhalla);
		expected.getItems().add(pFunny);

		customers.add(new Customer(id, "jordan", "harrison"));
		items.add(wdLegion);

		when(customerDAO.readAll()).thenReturn(customers);
		when(utils.getLong()).thenReturn(id, item_id, item_id);
		when(ordersDAO.create(any(Orders.class))).thenReturn(new Orders(1L, 1L, date, new ArrayList<>()));
		when(itemsDAO.readAll()).thenReturn(items);

		when(utils.getString()).thenReturn("Yes", "No");
		when(ordersDAO.readLatest()).thenReturn(expected);

		assertEquals(expected, ordersController.create());
		verify(customerDAO, times(1)).readAll();
		verify(utils, times(3)).getLong();
		verify(utils, times(2)).getString();
		verify(ordersDAO, times(1)).create(any(Orders.class));
		verify(itemsDAO, times(2)).readAll();
		verify(ordersDAO, times(1)).readLatest();

	}

	@Test
	public void testRead() {
		Utils util = new Utils();
		Date date = util.getDate();
		List<Orders> orders = new ArrayList<>();
		List<Items> items = new ArrayList<>();

		Items acValhalla = new Items(1L, "Valhalla", 476L, 19.62D);
		Items wdLegion = new Items(2L, "Legion", 356L, 8.99D);
		Items pFunny = new Items(3L, "Piers Humour", 678L, 0.01D);

		Orders firstOrder = new Orders(1L, 1L, date, items);
		Orders secondOrder = new Orders(2L, 1L, date, items);

		firstOrder.getItems().add(wdLegion);
		firstOrder.getItems().add(acValhalla);
		firstOrder.getItems().add(pFunny);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);
		secondOrder.getItems().add(wdLegion);

		orders.add(firstOrder);
		orders.add(secondOrder);

		for (Orders order : orders) {
			for (Items item : order.getItems()) {
				item.setStock(1L);
			}
		}

		when(ordersDAO.readAll()).thenReturn(orders);
		assertEquals(orders, ordersController.readAll());

		verify(ordersDAO, times(1)).readAll();

	}

	@Test
	public void testUpdate() {
		Utils util = new Utils();
		Date date = util.getDate();

		List<Customer> customers = new ArrayList<Customer>();
		List<Items> items = new ArrayList<Items>();
		Long id = 1L;
		Long item_id = 2L;
		Long order_id = 1L;

		Items wdLegion = new Items(item_id, "Legion", 356L, 8.99D);
		Orders now = new Orders(order_id, item_id, date, items);

		now.getItems().add(wdLegion);
		now.getItems().add(wdLegion);

		customers.add(new Customer(id, "Piers", "Barber"));
		items.add(wdLegion);

		when(utils.getLong()).thenReturn(order_id, order_id, item_id, item_id);
		when(ordersDAO.readOrders(order_id)).thenReturn(now, now, now);

		when(utils.getString()).thenReturn("add, ", "delete, ", "yes or ", "exit");
		assertEquals(now, ordersController.update());

		verify(utils, times(1)).getLong();
		verify(utils, times(4)).getString();

	}

	@Test
	public void testDelete() {
		Utils util = new Utils();
		Date date = util.getDate();

		Long item_id = 2L;
		Long order_id = 1L;
		Items wdLegion = new Items(item_id, "Legion", 356L, 8.99D);

		List<Items> items = new ArrayList<>();
		List<Orders> orders = new ArrayList<>();

		Orders order = new Orders(order_id, 1L, date, items);
		order.getItems().add(wdLegion);
		orders.add(order);

		when(utils.getLong()).thenReturn(order_id);
		when(ordersDAO.delete(order_id)).thenReturn(1);

		assertEquals(1, ordersController.delete());
		
		verify(ordersDAO, times(1)).delete(order_id);
		verify(utils, times(1)).getLong();

	}

}
