package com.yash.msdemo.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yash Joshi
 * @date created on 13-Sep-2021
 */
@SuppressWarnings("serial")
public class Product implements Serializable {

	private Long id;
	private String name;
	private Integer qty;
	private String origin;
	private User user;

	public Product() {
		super();
	}

	public Product(Long id, String name, Integer qty, String origin, User user) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.origin = origin;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, origin, qty);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(origin, other.origin)
				&& Objects.equals(qty, other.qty);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", qty=" + qty + ", origin=" + origin + "]";
	}

}
