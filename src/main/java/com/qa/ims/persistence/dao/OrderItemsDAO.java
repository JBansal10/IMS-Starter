package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAO implements Dao<OrderItems> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderItems modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderitemsId = resultSet.getLong("orderitems_id");
		Long fkOrderId = resultSet.getLong("fk_order_id");
		Long fkItemId = resultSet.getLong("fk_item_id");
		Long quantity = resultSet.getLong("quantity");
		Double cost = resultSet.getDouble("cost");
		return new OrderItems(orderitemsId, fkOrderId, fkItemId, quantity, cost);
	}

	@Override
	public List<OrderItems> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderitems");) {
			List<OrderItems> orderItems = new ArrayList<>();
			while (resultSet.next()) {
				orderItems.add(modelFromResultSet(resultSet));
			}
			return orderItems;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return new ArrayList<>();
	}

	public OrderItems readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderitems ORDER BY orderitems_id DESC");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems create(OrderItems orderItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orderitems (fk_order_id, fk_item_id, quantity, cost) VALUES('"
					+ orderItems.getFkOrderId() + "','" + orderItems.getFkItemId() + "','" + orderItems.getQuantity()
					+ "','" + orderItems.getCost() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public OrderItems readOrderItems(Long fkOrderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderitems WHERE fk_order_id = " + fkOrderId);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItems update(OrderItems orderItems) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.execute("UPDATE orderitems SET fk_order_id = '" + orderItems.getFkOrderId() + "', fk_item_id = '"
					+ orderItems.getFkItemId() + "', quantity ='" + orderItems.getQuantity() + "', cost = '"
					+ orderItems.getCost() + "'WHERE orderitems_Id = " + orderItems.getOrderitemsId());
			return readOrderItems(orderItems.getFkOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.debug(e.getMessage());
		}
		return null;
	}

	public OrderItems readOrderItemsSingle(Long orderitemsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderitems WHERE orderitemsId = " + orderitemsId);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long orderitemsId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("DELETE FROM orderitems WHERE orderitems_id = " + orderitemsId);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public Double totalPriceCalc(Long quantity) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT SUM(orderitems.quantity*item.price) AS cost FROM orderitems JOIN item ON "
								+ "orderitems.fk_item_id = item.item_id WHERE quantity =" + quantity);) {
			resultSet.next();
			return resultSet.getDouble("cost");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return 0D;
	}

}
