package com.yash.msdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.converter.UserConverter;
import com.yash.msdemo.entity.Product;
import com.yash.msdemo.model.ProductDTO;
import com.yash.msdemo.repository.ProductRepository;
import com.yash.msdemo.utils.IdGenerator;
import com.yash.msdemo.utils.ProductValidator;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@Service
public class ProductService implements IProductService {

	@Inject
	ProductRepository productRepository;

	@Override
	public Optional<Product> findByObject(Product product) {
		return productRepository
				.findAll().stream().filter(it -> it.getName().equalsIgnoreCase(product.getName())
						&& it.getQty() == product.getQty() && it.getOrigin().equalsIgnoreCase(product.getOrigin()))
				.findAny();
	}

	@Override
	public Optional<Product> findById(Long productId) {
		return productRepository.findById(productId);
	}

	@Override
	public Optional<List<Product>> getAll() {
		List<Product> products = productRepository.findAll();
		if (products != null && !products.isEmpty()) {
			return Optional.of(products);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Product> save(Product product) {
		product.setId((long) IdGenerator.generate());
		ProductValidator.validate(product);
		if (findByObject(product).isPresent()) {
			return Optional.empty();
		} else {
			try {
				productRepository.save(product);
				return Optional.of(product);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(Constants.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public Optional<Product> buyProduct(Long userId, Long productId) {
		try {
			productRepository.updateProductOwnerId(userId, productId);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		return productRepository.findById(productId);
	}

	@Override
	public Optional<List<Product>> getUserProduct(Long productId, Long userId) {
		return productRepository.findByIdAndUserId(productId, userId);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public Optional<Product> update(Product product) {
		ProductValidator.validate(product);
		if (productRepository.findById(product.getId()).isPresent()) {
			try {
				productRepository.save(product);
				return Optional.of(product);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(Constants.ERROR_MESSAGE);
			}
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Product> deleteUserProduct(Long productId) {
		try {
			productRepository.updateProductOwnerId(null, productId);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		return productRepository.findById(productId);
	}

	@Override
	public Optional<List<ProductDTO>> getAllWithUser() {
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productsDto = new ArrayList<>();
		products.forEach(it -> {
			ProductDTO productDto = new ProductDTO();
			productDto.setId(it.getId());
			productDto.setName(it.getName());
			productDto.setQty(it.getQty());
			productDto.setOrigin(it.getOrigin());
			productDto.setUser(UserConverter.convert(it.getUser()));
			productsDto.add(productDto);
		});
		return Optional.of(productsDto);
	}

}
