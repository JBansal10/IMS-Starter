package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderItemsDAO;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;

public class OrderItemsController implements CrudController<OrderItems> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderItemsDAO orderItemsDAO;
	private Utils utils;

	public OrderItemsController(OrderItemsDAO orderItemsDAO, Utils utils) {
		super();
		this.orderItemsDAO = orderItemsDAO;
		this.utils = utils;
	}

	@Override
	public List<OrderItems> readAll() {
		List<OrderItems> orderItem = orderItemsDAO.readAll();
		for (OrderItems orderItems : orderItem) {
			LOGGER.info(orderItems.toString());
		}
		return orderItem;
	}

	@Override
	public OrderItems create() {

		LOGGER.info("Please input the order number");
		Long fkOrderId = utils.getLong();
		LOGGER.info("Please input the product id which the customer is purchasing");
		Long fkItemId = utils.getLong();
		LOGGER.info("Please enter the amount which the customer is purchasing");
		Long quantity = utils.getLong();
		Double cost = orderItemsDAO.totalPriceCalc(quantity);
		LOGGER.info("The cost of this product is: " + cost);
		OrderItems orderItems = orderItemsDAO.create(new OrderItems(fkOrderId, fkItemId, quantity, cost));
		LOGGER.info("Order Items updated!");
		return orderItems;
	}

	@Override
	public OrderItems update() {
		LOGGER.info("Please enter the ID of Order Items which you would like to update");
		Long orderitemsId = utils.getLong();
		LOGGER.info("Please input the order id");
		Long fkOrderId = utils.getLong();
		LOGGER.info("Please input the product id which the customer is purchasing");
		Long fkItemId = utils.getLong();
		LOGGER.info("Pleas enter the amount which the customer is purchasing");
		Long quantity = utils.getLong();
		LOGGER.info("The cost of this product is: ");
		Double cost = utils.getDouble();
		OrderItems orderItems = orderItemsDAO.update(new OrderItems(orderitemsId, fkOrderId, fkItemId, quantity, cost));
		LOGGER.info("Order Items Updated");
		return orderItems;
	}

	@Override
	public int delete() {
		LOGGER.info("Please input the ID of the order item which you would like to delete");
		Long orderitemsId = utils.getLong();
		return orderItemsDAO.delete(orderitemsId);
	}

}
