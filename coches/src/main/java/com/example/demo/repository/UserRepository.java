package com.example.demo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	@Query("SELECT * FROM USERS WHERE USER_NAME= :userName LIMIT 1")
	public User findUserByUserName(@Param("userName")String userName);
}
