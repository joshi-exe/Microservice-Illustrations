package com.yash.msdemo.converter;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.msdemo.entity.Product;
import com.yash.msdemo.model.ProductDTO;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public final class ProductConverter {

	public static ProductDTO convert(Product product) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(product, new TypeReference<ProductDTO>() {
		});
	}

	public static Product convert(ProductDTO productDTO) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(productDTO, new TypeReference<Product>() {
		});
	}

	public static List<ProductDTO> convertEntityList(List<Product> productEntityList) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(productEntityList, new TypeReference<List<ProductDTO>>() {
		});
	}

	public static List<Product> convertDTOList(List<ProductDTO> productDTOList) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(productDTOList, new TypeReference<List<Product>>() {
		});
	}

}
