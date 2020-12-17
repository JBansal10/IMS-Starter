package com.qa.ims.persistence.domain;

import java.sql.Date;

public class Orders {
	
	private Long order_id;
	private Long fkCustomerId;
	private Date datePlaced;
	private Double totPrice;
	
	public Orders(Long fkCustomerId, Date datePlaced, Double totPrice) {
		this.datePlaced = datePlaced;
		this.totPrice = totPrice;
		this.fkCustomerId = fkCustomerId;
	}
	
	public Orders(Long order_id, Long fkCustomerId, Date datePlaced, Double totPrice) {
		this.order_id = order_id;
		this.datePlaced = datePlaced;
		this.totPrice = totPrice;
		this.fkCustomerId = fkCustomerId;
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

	public Double getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(Double totPrice) {
		this.totPrice = totPrice;
	}

	public Long getFkCustomerId() {
		return fkCustomerId;
	}

	public void setFkCustomerId(Long fkCustomerId) {
		this.fkCustomerId = fkCustomerId;
	}

	@Override
	public String toString() {
		return "order_id=" + order_id + "fkCustomerId =" + fkCustomerId + ", datePlaced=" + datePlaced + ", totPrice=" + totPrice;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if(fkCustomerId == null) {
			if (other.fkCustomerId != null)
				return false;
		} else if (!fkCustomerId.equals(other.fkCustomerId))
			return false;
		if(datePlaced == null) {
			if(other.datePlaced != null)
				return false;
		} else if (!datePlaced.equals(other.datePlaced))
			return false;
		if (order_id == null) {
			if(other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (totPrice == null) {
			if (other.totPrice != null)
				return false;
		} else if (!totPrice.equals(other.totPrice))
			return false;
		return true;
	}
	
	
}
