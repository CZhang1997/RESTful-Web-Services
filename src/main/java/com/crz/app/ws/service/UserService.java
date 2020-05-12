package com.crz.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.crz.app.ws.shared.dto.UserDto;

/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 * This is an interface for User Service
 */
public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUserByUerId(String id);
	UserDto updateUser(String userId, UserDto userDto);
	UserDto getUser(String email);
	void deleteUser(String userId);
	
}
