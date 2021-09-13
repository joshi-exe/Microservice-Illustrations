package com.yash.msdemo.converter;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.msdemo.entity.User;
import com.yash.msdemo.model.UserDTO;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public final class UserConverter {

	public static UserDTO convert(User user) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(user, new TypeReference<UserDTO>() {
		});
	}

	public static User convert(UserDTO userDTO) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(userDTO, new TypeReference<User>() {
		});
	}

	public static List<UserDTO> convertEntityList(List<User> userEntityList) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(userEntityList, new TypeReference<List<UserDTO>>() {
		});
	}

	public static List<User> convertDTOList(List<UserDTO> userDTOList) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(userDTOList, new TypeReference<List<User>>() {
		});
	}

}
