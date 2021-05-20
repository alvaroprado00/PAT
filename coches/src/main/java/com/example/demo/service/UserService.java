package com.example.demo.service;

import com.example.demo.model.CarReference;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.User;

public interface UserService{
	
	public User registerUser(User u);
	public User loginUser(LoginRequest loginReq);
	public User addCarReference(long id, CarReference cr);
}
