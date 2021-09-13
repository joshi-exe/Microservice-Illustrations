package com.yash.msdemo.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.model.Product;
import com.yash.msdemo.model.User;

/**
 * @author Yash Joshi
 * @date created on 13-Sep-2021
 */
@FeignClient(value = "demo-dbservice", url = "http://localhost:2222/")
@RequestMapping("/api")
public interface DemoFeignClient {

	// default page
	@GetMapping("/")
	public String defaultPage(@RequestParam(required = false) String name);

	// user registration without product
	@PostMapping("/users")
	public User saveUser(@RequestBody User user);

	// home
	@GetMapping("/products")
	public List<Product> getAllProducts();

	// all user products
	@GetMapping("/users/{id}/products")
	public List<Product> getAllUserProducts(@PathVariable("id") Long userId);

	// save product
	@PostMapping("/products")
	public Product saveProduct(@RequestBody Product product);

	// buy a product
	@PutMapping("/products/{productId}/buy")
	public User buyProduct(@PathVariable Long productId,
			@RequestParam(name = Constants.USER_ID, required = true) Long userId);

	// get some product of some user
	@GetMapping("/users/{userId}/products/{productId}")
	public List<Product> getUserProduct(@PathVariable Long userId, @PathVariable Long productId);

	// add user with product
	@PostMapping("/users/products")
	public User saveUserProduct(@RequestBody User user);

	// get all users
	@GetMapping("/users")
	public List<User> getAllUsers();

	// get a user
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id);

	// update a user
	@PutMapping("/users")
	public User updateUser(@RequestBody User user);

	// delete a user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id);

	// delete a product
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Long id);

	// update a product
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product);

	// delete user product
	@PutMapping("/users/{userId}/products/{productId}")
	public User deleteProduct(@PathVariable Long userId, @PathVariable Long productId);

	// get by user name and password
	@PostMapping("/users/auth")
	public User authenticateUser(@RequestBody User user);

	// all products with user info
	@GetMapping("/products/users")
	public List<Product> getProductsWithUser();
}
