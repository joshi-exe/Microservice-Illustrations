package com.yash.msdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.msdemo.entity.Product;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

	Optional<Product> findByName(String name);

	Optional<List<Product>> findByIdAndUserId(Long productId, Long userId);

}
