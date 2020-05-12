package com.crz.app.ws.exceptions;

/**
 * @author Churong Zhang
 * @Date 5/11/2020
 * @Email churongzhang1997@gmail.com
 */

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message)
    {
        super(message);
    }

}
