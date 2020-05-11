package com.crz.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * @author Churong Zhang
 * @Date May 10, 2020
 * @Email churongzhang1997@gmail.com
 * This class contains helper functions 
 */

@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = 
			"0123456789ABCDEFGHIJKLMNOPQRSTOVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generateUserId(int length)
	{
		return generateRandomString(length);
	}
	
	private String generateRandomString(int length)
	{
		StringBuilder returnValue = new StringBuilder(length);
		
		for(int i = 0; i < length; i ++)
		{
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

}
