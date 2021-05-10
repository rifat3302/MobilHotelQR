package com.example.mobilhotelqr.Core;

public class ApiUtils {

    public static final String BASE_URL="https://seocuyuz.online/mobdet/api/";

    public static RetrofitProcess getOccupancy(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }
}

