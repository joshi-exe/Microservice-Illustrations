package com.yash.msdemo.utils;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.entity.User;
import com.yash.msdemo.exception.MandatoryFieldsException;
import com.yash.msdemo.exception.PasswordException;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public final class UserValidator {

	public static void validate(User user) {
		String userName = user.getUserName();
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new MandatoryFieldsException(Constants.PASSWORD);
		} else if (userName == null || userName.isEmpty()) {
			throw new MandatoryFieldsException(Constants.USERNAME);
		} else if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new PasswordException();
		}
	}
}
