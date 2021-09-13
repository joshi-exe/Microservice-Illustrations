package com.yash.msdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.converter.ProductConverter;
import com.yash.msdemo.converter.UserConverter;
import com.yash.msdemo.entity.Product;
import com.yash.msdemo.entity.User;
import com.yash.msdemo.exception.ProductFoundException;
import com.yash.msdemo.exception.ProductNotFoundException;
import com.yash.msdemo.exception.UserFoundException;
import com.yash.msdemo.exception.UserNotFoundException;
import com.yash.msdemo.model.ProductDTO;
import com.yash.msdemo.model.UserDTO;
import com.yash.msdemo.service.IProductService;
import com.yash.msdemo.service.IUserService;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@RestController
@RequestMapping("/api")
public class UserProductController {

	@Inject
	IUserService userService;

	@Inject
	IProductService productService;

	// default page
	@GetMapping("/")
	public String defaultPage(@RequestParam(required = false) String name) {
		return "Hello, " + (name != null ? (!name.isEmpty() ? name : "products and users") : "products and users")
				+ "!";
	}

	// user registration without product
	@PostMapping("/users")
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto) {
		User user = UserConverter.convert(userDto);
		User createdUser = userService.save(user).orElseThrow(() -> new UserFoundException(user.getUserName()));
		createdUser.setConfirmPassword(null);
		return new ResponseEntity<UserDTO>(UserConverter.convert(createdUser), HttpStatus.CREATED);
	}

	// home
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<Product> products = productService.getAll().orElseThrow(() -> new ProductNotFoundException());
		return new ResponseEntity<List<ProductDTO>>(ProductConverter.convertEntityList(products), HttpStatus.OK);
	}

	// all user products
	@GetMapping("/users/{id}/products")
	public ResponseEntity<List<ProductDTO>> getAllUserProducts(@PathVariable("id") Long userId) {
		List<ProductDTO> products = userService.getProducts(userId).orElseThrow(() -> new ProductNotFoundException());
		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
	}

	// save product
	@PostMapping("/products")
	public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDto) {
		Product product = ProductConverter.convert(productDto);
		Product createdProduct = productService.save(product).orElseThrow(() -> new ProductFoundException());
		return new ResponseEntity<ProductDTO>(ProductConverter.convert(createdProduct), HttpStatus.CREATED);
	}

	// buy a product
	@PutMapping("/products/{productId}/buy")
	public ResponseEntity<UserDTO> buyProduct(@PathVariable Long productId,
			@RequestParam(name = Constants.USER_ID, required = true) Long userId) {
		userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
		productService.buyProduct(userId, productId).orElseThrow(() -> new RuntimeException(Constants.ERROR_MESSAGE));
		UserDTO user = UserConverter.convert(userService.findById(userId).get());
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	// get some product of some user
	@GetMapping("/users/{userId}/products/{productId}")
	public ResponseEntity<List<ProductDTO>> getUserProduct(@PathVariable Long userId, @PathVariable Long productId) {
		List<Product> products = productService.getUserProduct(productId, userId)
				.orElseThrow(() -> new ProductNotFoundException(productId));
		return new ResponseEntity<List<ProductDTO>>(ProductConverter.convertEntityList(products), HttpStatus.OK);
	}

	// add user with product
	@PostMapping("/users/products")
	public ResponseEntity<UserDTO> saveUserProduct(@RequestBody UserDTO userDto) {
		User user = UserConverter.convert(userDto);
		User createdUser = userService.save(user).orElseThrow(() -> new UserFoundException(user.getUserName()));
		Optional.ofNullable(createdUser.getProducts()).ifPresent(it -> it.forEach(productService::save));
		createdUser.setConfirmPassword(null);
		return new ResponseEntity<UserDTO>(UserConverter.convert(createdUser), HttpStatus.CREATED);
	}

	// get all users
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = userService.getAll().orElseThrow(() -> new UserNotFoundException());
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	// get a user
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return new ResponseEntity<UserDTO>(UserConverter.convert(user), HttpStatus.OK);
	}

	// update a user
	@PutMapping("/users")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto) {
		User user = UserConverter.convert(userDto);
		User updatedUser = userService.update(user).orElseThrow(() -> new UserNotFoundException(user.getId()));
		return new ResponseEntity<UserDTO>(UserConverter.convert(updatedUser), HttpStatus.OK);
	}

	// delete a user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		userService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// delete a product
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
		productService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// update a product
	@PutMapping("/products")
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDto) {
		Product product = ProductConverter.convert(productDto);
		Product updatedProduct = productService.update(product)
				.orElseThrow(() -> new ProductNotFoundException(product.getId()));
		return new ResponseEntity<ProductDTO>(ProductConverter.convert(updatedProduct), HttpStatus.OK);
	}

	// delete user product
	@PutMapping("/users/{userId}/products/{productId}")
	public ResponseEntity<UserDTO> deleteProduct(@PathVariable Long userId, @PathVariable Long productId) {
		userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
		productService.deleteUserProduct(productId).orElseThrow(() -> new RuntimeException(Constants.ERROR_MESSAGE));
		UserDTO user = UserConverter.convert(userService.findById(userId).get());
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	// get user by user name and password
	@PostMapping("/users/auth")
	public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO userDTO) {
		User user = userService.authenticate(userDTO).orElseThrow(() -> new UserNotFoundException());
		return new ResponseEntity<UserDTO>(UserConverter.convert(user), HttpStatus.OK);
	}

	// all products with user info
	@GetMapping("/products/users")
	public ResponseEntity<List<ProductDTO>> getProductsWithUser() {
		List<ProductDTO> products = productService.getAllWithUser().orElseThrow(() -> new ProductNotFoundException());
		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
	}

}
