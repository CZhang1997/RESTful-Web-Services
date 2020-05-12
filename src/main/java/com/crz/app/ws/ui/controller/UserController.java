package com.crz.app.ws.ui.controller;
import com.crz.app.ws.exceptions.UserServiceException;
import com.crz.app.ws.ui.model.response.*;
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
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		UserRest returnValue = new UserRest();
		if(userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue = new UserRest();
		if(userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);

		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);

		return returnValue;
	}
	
	@DeleteMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id)
	{
		OperationStatusModel returnValue = new OperationStatusModel();
		userService.deleteUser(id);
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
}
