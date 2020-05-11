package com.crz.app.ws.security;

import com.crz.app.ws.SpringApplicationContext;

/**
 * @author Churong Zhang
 * @Date May 11, 2020
 * @Email churongzhang1997@gmail.com
 */
public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000; // 10 DAYS
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
//	public static final String TOKEN_SECRET = "jf9i4jgu83nf10";

	public static String getTokenSecret()
	{	// in MobileAppWsApplication.java must have the AppProperties bean in order to do the following
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}

}
