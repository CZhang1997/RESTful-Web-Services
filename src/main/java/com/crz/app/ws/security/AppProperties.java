package com.crz.app.ws.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Churong Zhang
 * @Date 5/11/2020
 * @Email churongzhang1997@gmail.com
 */

@Component
public class AppProperties {
    @Autowired
    private Environment env;

    public String getTokenSecret()
    {
        return env.getProperty("tokenSecret");
    }
}
