package com.yash.msdemo.model;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Yash Joshi
 * @date created on 13-Sep-2021
 */
@SuppressWarnings("serial")
public class UserForm implements Serializable {

	private Long id;
	@NotEmpty(message = "{username.not.empty}")
	@Size(min = 3, max = 20, message = "{username.size}")
	private String userName;
	@NotEmpty(message = "{password.not.empty}")
	@Size(min = 3, max = 16, message = "{password.size}")
	private String password;
	private String confirmPassword;
	private Set<Product> products;

	private String message;
	private Boolean isFailure;

	public UserForm(Long id, String userName, String password, String confirmPassword, Set<Product> products) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.products = products;
	}

	public UserForm() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
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
		result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserForm other = (UserForm) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", products=" + products + "]";
	}

}
