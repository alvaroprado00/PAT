package info.jab.microservices.service;

import org.springframework.stereotype.Service;

import info.jab.microservices.model.UserChangePasswordRequest;
import info.jab.microservices.model.UserDetail;

@Service
public interface UserService {
	
	public UserDetail changePassword(UserChangePasswordRequest u);
}
