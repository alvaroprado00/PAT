package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CarReference;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public User registerUser(User u) {
		return userRepo.save(u);
	}



	@Override
	public User loginUser(LoginRequest loginReq) {
		
		String userName=loginReq.getUserName();
		
		User u =userRepo.findUserByUserName(userName);
		
		if( loginReq.getPassword().equals(u.getPassword())) {
			return u;
		}else {
			return null;
		}
		
		
	}



	@Override
	public User addCarReference(long id, CarReference cr) {
		User user= userRepo.findById(id).get();
		user.getRentedCars().add(cr);
		return userRepo.save(user);
	}

}
