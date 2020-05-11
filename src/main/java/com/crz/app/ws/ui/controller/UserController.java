package com.crz.app.ws.ui.controller;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 */
import org.springframework.http.MediaType;
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
	
	
	@GetMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String id)
	{
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUserByUerId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
				produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
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
