package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	private ItemsDAO itemsDAO;
	private Utils utils;
	
	public ItemsController (ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	@Override
	public Items create() {
		LOGGER.info("Please enter the name of the item");
		String itemName = utils.getString();
		LOGGER.info("Please input the stock available for " + itemName);
		Long stock = utils.getLong();
		LOGGER.info("Please input the price of " + itemName);
		Double price = utils.getDouble();
		Items item = itemsDAO.create(new Items(itemName, stock, price));
		LOGGER.info(itemName + " has been created");
		return item;
	}

	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the item which you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the name of the item");
		String itemName = utils.getString();
		LOGGER.info("Please input the stock available for " + itemName);
		Long stock = utils.getLong();
		LOGGER.info("Please input the price of " + itemName);
		Double price = utils.getDouble();
		Items item = itemsDAO.update(new Items(id, itemName, stock, price));
		LOGGER.info(itemName + " updated!");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item which you would like to delete");
		Long id = utils.getLong();
		return itemsDAO.delete(id);
	}

}
