package com.yash.msdemo.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable {

	private Long id;
	private String name;
	private Integer qty;
	private String origin;
	private UserDTO user;

	public ProductDTO(Long id, String name, Integer qty, String origin, UserDTO user) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.origin = origin;
		this.user = user;
	}

	public ProductDTO() {
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
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
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(origin, other.origin)
				&& Objects.equals(qty, other.qty);
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", qty=" + qty + ", origin=" + origin + "]";
	}

}
