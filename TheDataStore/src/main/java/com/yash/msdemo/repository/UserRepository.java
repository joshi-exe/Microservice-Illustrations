package com.yash.msdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.msdemo.entity.User;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

	Optional<User> findByUserNameAndPassword(String userName, String password);

}
