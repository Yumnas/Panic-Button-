package com.example.panicbuttonproject.UserLoginAndRegistration.Response;

public class BaseResponse<T> {
    private String responseCode;
    private String responseDesc;
    private T data;

    public BaseResponse(String responseCode, String responseDesc, T data) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
