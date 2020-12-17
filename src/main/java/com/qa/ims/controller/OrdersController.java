package com.qa.ims.controller;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrdersController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
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
		LOGGER.info("Please input the ID of the customer who is making the order");
		Long fkCustomerId = utils.getLong();
		LOGGER.info("This is the date the order was purchased on: " + utils.getDate() + "\n");
		Date datePlaced = utils.getDate();
		LOGGER.info("Please enter the total price of all the items in the order");
		Double totPrice = utils.getDouble();
		Orders order = orderDAO.create(new Orders(fkCustomerId, datePlaced, totPrice));
		LOGGER.info("Order has been successfully added");
		return order;
	}

	@Override
	public Orders update() {
		LOGGER.info("Please enter the ID of the order which you would like to update");
		Long order_id = utils.getLong();
		Long fkCustomerId = utils.getLong();
		LOGGER.info("This is the date the order was purchased on: " + utils.getDate() + "\n");
		Date datePlaced = utils.getDate();
		LOGGER.info("Please enter the total price of all the items in the order");
		Double totPrice = utils.getDouble();
		Orders order = orderDAO.update(new Orders(order_id, fkCustomerId, datePlaced, totPrice));
		LOGGER.info("Order has been updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the order which you would like to delete");
		Long order_id = utils.getLong();
		return orderDAO.delete(order_id);
	}

}
