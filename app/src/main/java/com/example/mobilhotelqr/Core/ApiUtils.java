package com.example.mobilhotelqr.Core;

public class ApiUtils {

    public static final String BASE_URL="https://www.burak.social/mobdet/api/";

    public static RetrofitProcess getOccupancy(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess getAllMenu(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess saveOrder(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess getOrderHistory(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess qrControl(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess loginAfterPrivateKey(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }

    public static RetrofitProcess logoutControlServiceForMobile(){
        return  RetrofitClient.getClient(BASE_URL).create(RetrofitProcess.class);
    }
}

