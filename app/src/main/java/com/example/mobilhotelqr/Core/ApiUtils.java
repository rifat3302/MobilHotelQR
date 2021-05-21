package com.example.mobilhotelqr.Core;

public class ApiUtils {

    public static final String BASE_URL="https://www.burak.social/api/";
    //public static final String BASE_URL="https://seocuyuz.online/mobdet/api/";

    public static RetrofitProcess getOccupancy(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess getAllMenu(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess saveOrder(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }
}

