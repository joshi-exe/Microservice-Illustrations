package com.yash.msdemo.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Yash Joshi
 * @date created on 13-Sep-2021
 */
@SuppressWarnings("serial")
public class ProductForm implements Serializable {

	private Long id;
	@NotEmpty(message = "{product.name.not.empty}")
	private String name;
	@NotNull(message = "{product.qty.not.null}")
	@Max(value = 5, message = "{product.qty}")
	@Min(value = 1, message = "{product.qty}")
	private Integer qty;
	@NotEmpty(message = "{product.origin.not.empty}")
	private String origin;
	private User user;

	private String message;
	private Boolean isFailure;

	public ProductForm(Long id, String name, Integer qty, String origin, User user) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.origin = origin;
		this.user = user;
	}

	public ProductForm() {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsFailure() {
		return isFailure;
	}

	public void setIsFailure(Boolean isFailure) {
		this.isFailure = isFailure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qty == null) ? 0 : qty.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductForm other = (ProductForm) obj;
		if (qty == null) {
			if (other.qty != null)
				return false;
		} else if (!qty.equals(other.qty))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", qty=" + qty + ", origin=" + origin + "]";
	}

}
