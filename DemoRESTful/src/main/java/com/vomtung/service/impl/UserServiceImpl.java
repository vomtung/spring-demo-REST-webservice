package com.vomtung.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vomtung.dao.UserDAO;
import com.vomtung.entity.User;
import com.vomtung.service.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	public User findById(long id){
		return userDAO.findById(id);
	}
	
	public User findByUserName(String username){
		return userDAO.findByUserName(username);
	};
	
}
