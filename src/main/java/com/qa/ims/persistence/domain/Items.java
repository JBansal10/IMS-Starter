package com.qa.ims.persistence.domain;

public class Items {
	private Long item_id;
	private String itemName;
	private Long stock;
	private Double price;

	public Items(String itemName, Long stock, Double price) {
		this.itemName = itemName;
		this.stock = stock;
		this.price = price;
	}

	public Items(Long item_id, String itemName, Long stock, Double price) {
		this.item_id = item_id;
		this.itemName = itemName;
		this.stock = stock;
		this.price = price;

	}

	public Long getitemId() {
		return item_id;
	}

	public void setId(Long item_id) {
		this.item_id = item_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "item_id =" + item_id + ", itemName=" + itemName + ", stock =" + stock + ", price =" + price;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
