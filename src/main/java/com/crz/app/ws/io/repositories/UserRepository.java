package com.crz.app.ws.io.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.crz.app.ws.io.entity.UserEntity;

/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 * This interface manage the database
 */


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	UserEntity findByUserId(String id);

	void delete(UserEntity entity);
	UserEntity findByFirstName(String firstName);
	
}
