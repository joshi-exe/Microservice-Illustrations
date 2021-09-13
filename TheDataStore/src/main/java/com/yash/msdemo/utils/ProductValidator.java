package com.yash.msdemo.utils;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.entity.Product;
import com.yash.msdemo.exception.IllegalQtyException;
import com.yash.msdemo.exception.MandatoryFieldsException;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public final class ProductValidator {

	public static void validate(Product product) {
		Integer qty = product.getQty();
		if (qty == null) {
			throw new MandatoryFieldsException(Constants.PRODUCT_QTY);
		} else if (product.getName() == null || product.getName().isEmpty()) {
			throw new MandatoryFieldsException(Constants.PRODUCT_NAME);
		} else if (product.getOrigin() == null || product.getOrigin().isEmpty()) {
			throw new MandatoryFieldsException(Constants.PRODUCT_ORIGIN);
		} else if (qty < 1 || qty > 5) {
			throw new IllegalQtyException();
		}
	}

}
