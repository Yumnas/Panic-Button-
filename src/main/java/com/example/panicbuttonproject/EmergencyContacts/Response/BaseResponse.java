package com.example.panicbuttonproject.EmergencyContacts.Response;

public class BaseResponse<T> {
    private String responseCode;
    private String responseDec;
    private T data;

    public BaseResponse(String responseCode, String responseDec, T data) {
        this.responseCode = responseCode;
        this.responseDec = responseDec;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDec() {
        return responseDec;
    }

    public void setResponseDec(String responseDec) {
        this.responseDec = responseDec;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
