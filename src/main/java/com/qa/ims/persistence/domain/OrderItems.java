package com.qa.ims.persistence.domain;

public class OrderItems {

	private Long orderItemsId;
	private Long fkOrderId;
	private Long fkItemId;
	private Long quantity;

	public OrderItems(Long fkOrderId, Long fkItemId, Long quantity) {
		this.fkOrderId = fkOrderId;
		this.fkItemId = fkItemId;
		this.quantity = quantity;
	}

	public OrderItems(Long orderItemsId, Long fkOrderId, Long fkItemId, Long quantity) {
		this.orderItemsId = orderItemsId;
		this.fkOrderId = fkOrderId;
		this.fkItemId = fkItemId;
		this.quantity = quantity;
	}

	public Long getOrderItemsId() {
		return orderItemsId;
	}

	public void setOrderItemsId(Long orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	public Long getFkOrderId() {
		return fkOrderId;
	}

	public void setFkOrderId(Long fkOrderId) {
		this.fkOrderId = fkOrderId;
	}

	public Long getFkItemId() {
		return fkItemId;
	}

	public void setFkItemId(Long fkItemId) {
		this.fkItemId = fkItemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "orderItemsId=" + orderItemsId + ", fkOrderId=" + fkOrderId + ", fkItemId=" + fkItemId + ", quantity="
				+ quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItems other = (OrderItems) obj;
		if (orderItemsId == null) {
			if (other.orderItemsId != null)
				return false;
		} else if (!orderItemsId.equals(other.orderItemsId))
			return false;
		if (fkOrderId == null) {
			if (other.fkOrderId != null)
				return false;
		} else if (!fkOrderId.equals(other.fkOrderId))
			return false;
		if (fkItemId == null) {
			if (other.fkItemId != null)
				return false;
		} else if (!fkItemId.equals(other.fkItemId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;

		return true;
	}

}
