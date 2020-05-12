package com.crz.app.ws.ui.model.response;

/**
 * @Author Churong Zhang
 * @Date 5/11/2020
 * @Email churongzhang1997@gmail.com
 */


public class OperationStatusModel {
    private String operationResult;
    private String OperationName;

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
