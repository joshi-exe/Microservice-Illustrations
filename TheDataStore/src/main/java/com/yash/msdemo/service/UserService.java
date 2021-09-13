package com.yash.msdemo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.converter.ProductConverter;
import com.yash.msdemo.converter.UserConverter;
import com.yash.msdemo.entity.Product;
import com.yash.msdemo.entity.User;
import com.yash.msdemo.exception.UserNotFoundException;
import com.yash.msdemo.model.ProductDTO;
import com.yash.msdemo.model.UserDTO;
import com.yash.msdemo.repository.UserRepository;
import com.yash.msdemo.utils.IdGenerator;
import com.yash.msdemo.utils.UserValidator;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@Service
public class UserService implements IUserService {

	@Inject
	UserRepository userRepository;

	@Override
	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> save(User user) {
		user.setId((long) IdGenerator.generate());
		UserValidator.validate(user);
		if (userRepository.findByUserName(user.getUserName()).isPresent()) {
			return Optional.empty();
		} else {
			try {
				userRepository.save(user);
				return Optional.of(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(Constants.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public Optional<List<ProductDTO>> getProducts(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		List<Product> products = user.getProducts().stream().collect(Collectors.toList());
		if (products != null && !products.isEmpty()) {
			return Optional.of(ProductConverter.convertEntityList(products));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<UserDTO>> getAll() {
		List<UserDTO> users = UserConverter.convertEntityList(userRepository.findAll());
		if (users != null && !users.isEmpty()) {
			return Optional.of(users);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> update(User user) {
		UserValidator.validate(user);
		if (userRepository.findById(user.getId()).isPresent()) {
			try {
				userRepository.save(user);
				return Optional.of(user);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(Constants.ERROR_MESSAGE);
			}
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> authenticate(UserDTO user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}
}
