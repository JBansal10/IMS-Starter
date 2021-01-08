package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.List;

public class Orders {

	private Long order_id;
	private Long fkCustomerId;
	private Date datePlaced;
	private List<Items> items;

	public Orders(Long fkCustomerId, Date datePlaced) {
		this.datePlaced = datePlaced;
		this.fkCustomerId = fkCustomerId;
	}

	public Orders(Long fkCustomerId, Date datePlaced, List<Items> items) {
		this.datePlaced = datePlaced;
		this.fkCustomerId = fkCustomerId;
		this.items = items;
	}

	public Orders(Long order_id, Long fkCustomerId, Date datePlaced, List<Items> items) {
		this.order_id = order_id;
		this.datePlaced = datePlaced;
		this.fkCustomerId = fkCustomerId;
		this.items = items;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Date getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(Date datePlaced) {
		this.datePlaced = datePlaced;
	}

	public Long getFkCustomerId() {
		return fkCustomerId;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public void setFkCustomerId(Long fkCustomerId) {
		this.fkCustomerId = fkCustomerId;
	}

	@Override
	public String toString() {
		String lines = "\n";
		double totPrice = 0.0;
		for (Items item : items) {
			totPrice += item.getPrice();
			lines += item.toString() + " \n";
		}
		return "Orders:" + "\n" + "order_id =" + order_id + ", fkCustomerId =" + fkCustomerId + ", datePlaced ="
				+ datePlaced + ", totPrice =" + totPrice + "\n " + " Items: " + lines;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (fkCustomerId == null) {
			if (other.fkCustomerId != null)
				return false;
		} else if (!fkCustomerId.equals(other.fkCustomerId))
			return false;
		if (datePlaced == null) {
			if (other.datePlaced != null)
				return false;
		} else if (!datePlaced.toString().equals(other.datePlaced.toString()))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (items == null) {
			if (items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

}
