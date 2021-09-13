package com.yash.msdemo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@Transactional
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void updateProductOwnerId(Long userId, Long productId) {
		String query = "update Product p set PRODUCT_OWNER_ID = ?1 where product_id = ?2";
		entityManager.createNativeQuery(query).setParameter(1, userId).setParameter(2, productId).executeUpdate();
	}
}
