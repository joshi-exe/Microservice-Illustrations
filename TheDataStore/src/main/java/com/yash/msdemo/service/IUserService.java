package com.yash.msdemo.service;

import java.util.List;
import java.util.Optional;

import com.yash.msdemo.entity.User;
import com.yash.msdemo.model.ProductDTO;
import com.yash.msdemo.model.UserDTO;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public interface IUserService {

	Optional<User> save(User user);

	Optional<List<ProductDTO>> getProducts(Long userId);

	Optional<List<UserDTO>> getAll();

	Optional<User> findById(Long userId);

	Optional<User> update(User user);

	void deleteById(Long id);

	Optional<User> authenticate(UserDTO user);
}