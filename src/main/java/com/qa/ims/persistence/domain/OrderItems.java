package com.qa.ims.persistence.domain;

public class OrderItems {

	private Long orderitemsId;
	private Long fkOrderId;
	private Long fkItemId;
	private Long quantity;
	private Double cost;

	public OrderItems(Long fkOrderId, Long fkItemId, Long quantity, Double cost) {
		this.fkOrderId = fkOrderId;
		this.fkItemId = fkItemId;
		this.quantity = quantity;
		this.cost = cost;
	}

	public OrderItems(Long orderitemsId, Long fkOrderId, Long fkItemId, Long quantity, Double cost) {
		this.orderitemsId = orderitemsId;
		this.fkOrderId = fkOrderId;
		this.fkItemId = fkItemId;
		this.quantity = quantity;
		this.cost = cost;
	}

	public Long getOrderitemsId() {
		return orderitemsId;
	}

	public void setOrderitemsId(Long orderitemsId) {
		this.orderitemsId = orderitemsId;
	}

	public Double getCost() {
		return cost;
	}

	public void setcost(Double cost) {
		this.cost = cost;
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
		return "orderitemsId =" + orderitemsId + ", cost=" + cost + ", fkOrderId=" + fkOrderId + ", fkItemId=" + fkItemId + ", quantity=" + quantity;
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
		if (orderitemsId == null) {
			if(other.orderitemsId != null)
			return false;
		} else if (!orderitemsId.equals(other.orderitemsId))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
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
