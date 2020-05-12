package com.crz.app.ws.ui.model.response;

import java.util.Date;

/**
 * @Author Churong Zhang
 * @Date 5/11/2020
 * @Email churongzhang1997@gmail.com
 */

public class ErrorMessage {

    private Date timestamp;
    private String Message;


    public ErrorMessage(Date timestamp, String message) {
        this.timestamp = timestamp;
        Message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
