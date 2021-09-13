package com.yash.msdemo.service;

import java.util.List;
import java.util.Optional;

import com.yash.msdemo.entity.Product;
import com.yash.msdemo.model.ProductDTO;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public interface IProductService {

	Optional<Product> findByObject(Product product);

	Optional<List<Product>> getAll();

	Optional<Product> save(Product product);

	Optional<Product> findById(Long productId);

	Optional<Product> buyProduct(Long userId, Long productId);

	Optional<List<Product>> getUserProduct(Long productId, Long userId);

	void deleteById(Long id);

	Optional<Product> update(Product product);

	Optional<Product> deleteUserProduct(Long productId);

	Optional<List<ProductDTO>> getAllWithUser();

}