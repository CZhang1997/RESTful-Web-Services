package com.crz.app.ws.ui.controller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 */
import org.springframework.web.bind.annotation.*;

import com.crz.app.ws.service.UserService;
import com.crz.app.ws.shared.dto.UserDto;
import com.crz.app.ws.ui.model.request.UserDetailsRequestModel;
import com.crz.app.ws.ui.model.response.UserRest;

/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 * This class deal with the incoming requests
 */

@RestController

@RequestMapping("users")	// http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping
	public String getUser()
	{
		return "get user was called";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		// 
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser()
	{
		return "update";
	}
	
	@DeleteMapping
	public String deleteUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		userService.deleteUser(userDetails.getEmail());
		return "deleted";
	}
}
