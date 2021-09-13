package com.yash.msdemo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
@Entity
@Table
public class Product implements Serializable {

	@Id
	@Column(name = "PRODUCT_ID")
	private Long id;

	@Column(name = "PRODUCT_NAME")
	private String name;

	@Column(name = "PRODUCT_QTY")
	private Integer qty;

	@Column(name = "PRODUCT_ORIGIN")
	private String origin;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "PRODUCT_OWNER_ID")
	private User user;

	public Product(Long id, String name, Integer qty, String origin, User user) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.origin = origin;
		this.user = user;
	}

	public Product() {
		super();
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
