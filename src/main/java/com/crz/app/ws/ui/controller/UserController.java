package com.crz.app.ws.ui.controller;
import com.crz.app.ws.exceptions.UserServiceException;
import com.crz.app.ws.io.entity.UserEntity;
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

import java.util.ArrayList;
import java.util.List;

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
	public OperationStatusModel getUser(@PathVariable String id)
	{

		UserRest userRest = new UserRest();
		UserDto userDto = userService.getUserByUerId(id);
		BeanUtils.copyProperties(userDto, userRest);
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.GET_USER_INFORMATION.name());
		returnValue.setUserRest(userRest);
		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
				produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception
	{
		UserRest userRest = new UserRest();
		if(userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto createUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createUser, userRest);

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.CREATE_USER_INFORMATION.name());
		returnValue.setUserRest(userRest);
		return returnValue;
	}
	
	@PutMapping(path="/{id}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest userRest = new UserRest();
		if(userDetails.getFirstName() == null || userDetails.getFirstName().isEmpty() || userDetails.getLastName() == null || userDetails.getLastName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, userRest);

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.UPDATE_USER_INFORMATEION.name());
		returnValue.setUserRest(userRest);
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id)
	{
		OperationStatusModel returnValue = new OperationStatusModel();
		userService.deleteUser(id);
		returnValue.setOperationName(RequestOperationName.DELETE_USER_iNFORMATION.name());
		return returnValue;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "25") int limit)
	{
		List<UserRest> returnValue = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page, limit);
		for(UserDto dto: users)
		{
			UserRest rest = new UserRest();
			BeanUtils.copyProperties(dto, rest);
			returnValue.add(rest);
		}
		return returnValue;
	}
}
