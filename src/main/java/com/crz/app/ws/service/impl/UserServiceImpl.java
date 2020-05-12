package com.crz.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.jmx.MBeanUtil;
import com.crz.app.ws.ui.model.response.ErrorMessage;
import com.crz.app.ws.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crz.app.ws.io.entity.UserEntity;
import com.crz.app.ws.io.repositories.UserRepository;
import com.crz.app.ws.service.UserService;
import com.crz.app.ws.shared.Utils;
import com.crz.app.ws.shared.dto.UserDto;

/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 * This class implements all User Service functions
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		// check if this record exist in the databases
		if(userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("Record already exisit.");
		
		// copy the input user to an entity object
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String publicUserID = utils.generateUserId(30);
		userEntity.setUserId(publicUserID);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null)
			throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " for the email " + email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUserByUerId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		UserDto returnValue = new UserDto();
		if(userEntity == null)
			throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " for the id " + userId);
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto returnValue = new UserDto();
        if(userEntity == null)
            throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if(userDto.getFirstName() != null && !userDto.getFirstName().isEmpty())
            userEntity.setFirstName(userDto.getFirstName());
        if(userDto.getLastName() != null && !userDto.getLastName().isEmpty())
            userEntity.setLastName(userDto.getLastName());
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserEntity, returnValue);
        return returnValue;
    }

    @Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserDto> returnValue = new ArrayList<>();
		if(page > 0)
			page --;
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();
		for(UserEntity entity: users)
		{
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(entity, dto);
			returnValue.add(dto);
		}
		return returnValue;
	}


	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null)
			throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);

	}
	
	
	

}
