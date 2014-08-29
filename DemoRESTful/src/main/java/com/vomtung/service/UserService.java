package com.vomtung.service;

import com.vomtung.entity.User;

public interface UserService {

	public User findById(long id);

	public User findByUserName(String username);
	
}
