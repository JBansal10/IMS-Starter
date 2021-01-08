package com.qa.ims.controller;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private ItemsDAO itemsDAO;
	private Utils utils;

	public OrdersController(OrderDAO orderDAO, Utils utils, CustomerDAO customerDAO, ItemsDAO itemsDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.customerDAO = customerDAO;
		this.itemsDAO = itemsDAO;
	}

	@Override
	public List<Orders> readAll() {
		List<Orders> orders = orderDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Orders create() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}

		LOGGER.info("Please input the ID of the customer who is making the order");
		Long fkCustomerId = utils.getLong();
		LOGGER.info("This is the date the order was purchased on: " + utils.getDate() + "\n");
		Date datePlaced = utils.getDate();
		Orders order = orderDAO.create(new Orders(fkCustomerId, datePlaced));

		String response;
		do {
			List<Items> items = itemsDAO.readAll();
			for (Items item : items) {
				LOGGER.info(item.toString());
			}

			LOGGER.info("Please input the ID of the item the customer is purchasing");
			Long itemId = utils.getLong();
			orderDAO.createLine(order.getOrder_id(), itemId);
			LOGGER.info("Would you like to add another item to the order (Answer yes or no)");
			response = utils.getString();
		} while (response.toLowerCase().equals("yes"));
		order = orderDAO.readLatest();

		LOGGER.info("Order has been successfully added");
		return order;
	}

	@Override
	public Orders update() {
		Long order_id = null;
		Orders current = null;
		do {
			LOGGER.info("Please enter the ID of the order which you would like to update");
			order_id = utils.getLong();
			current = orderDAO.readOrders(order_id);
		} while (current == null);
		boolean exit = false;

		do {
			current = orderDAO.readOrders(order_id);
			LOGGER.info(current.toString());
			LOGGER.info("Would you like to add or delete an item? Input exit to stop");
			String selection = utils.getString().toLowerCase();
			Long item_id;
			switch (selection) {
			case "delete":
				LOGGER.info("Please input the id of the item to delete");
				item_id = utils.getLong();
				orderDAO.deleteLine(current.getOrder_id(), item_id);
				break;

			case "add":
				List<Items> items = itemsDAO.readAll();
				for (Items item : items) {
					LOGGER.info(item.toString());
				}
				LOGGER.info("Please input the id of the item which you would like to add");
				item_id = utils.getLong();
				orderDAO.createLine(order_id, item_id);
				break;

			case "exit":
				exit = true;
				break;

			default:
				LOGGER.info("Invalid Selection, please input add, delete or exit");
				break;
			}
		} while (!exit);
		LOGGER.info("Order has been updated");
		return orderDAO.readOrders(order_id);

	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the order which you would like to delete");
		Long order_id = utils.getLong();
		return orderDAO.delete(order_id);
	}

}
