package com.crz.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 * This is an entity class that store data into the users table database
 */
@Entity(name="users") // the database to store in
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1193180824287782804L;

	@Id
	@GeneratedValue
	private long id;
	// can not not be null 
	@Column(nullable=false)
	private String userId;
	@Column(nullable=false, length = 50)
	private String firstName;
	@Column(nullable=false, length = 50)
	private String lastName;
	@Column(nullable=false, length = 120)
	private String email;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String encryptedPassword;
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus = false;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the encryptedPassword
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	/**
	 * @param encryptedPassword the encryptedPassword to set
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	/**
	 * @return the emailVerificationToken
	 */
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	/**
	 * @param emailVerificationToken the emailVerificationToken to set
	 */
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	/**
	 * @return the emailVerificationStatus
	 */
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	/**
	 * @param emailVerificationStatus the emailVerificationStatus to set
	 */
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	
	
}
