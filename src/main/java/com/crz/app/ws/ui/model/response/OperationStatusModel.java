package com.crz.app.ws.ui.model.response;

/**
 * @Author Churong Zhang
 * @Date 5/11/2020
 * @Email churongzhang1997@gmail.com
 */


public class OperationStatusModel {
    private String operationResult;
    private String OperationName;
    private UserRest userRest;
    private int statusCode;

    public OperationStatusModel()
    {
        statusCode = 200;
        operationResult = RequestOperationStatus.SUCCESS.name();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public UserRest getUserRest() {
        return userRest;
    }

    public void setUserRest(UserRest userRest) {
        this.userRest = userRest;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getOperationName() {
        return OperationName;
    }

    public void setOperationName(String operationName) {
        OperationName = operationName;
    }
}
