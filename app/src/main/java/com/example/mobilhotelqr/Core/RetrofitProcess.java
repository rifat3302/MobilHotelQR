package com.example.mobilhotelqr.Core;

import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.example.mobilhotelqr.PojoModels.OrderHistory.Datum;
import com.example.mobilhotelqr.PojoModels.OrderHistory.OrderHistory;
import com.example.mobilhotelqr.PojoModels.Response.ResponseData;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitProcess {

    @GET("mobilDashboardOccupancy")
    Call<OccupancyData> allDashboarOccupancy();

    @GET("mobilMenu")
    Call<MenuData> getAllMenu();

    @POST("saveOrder")
    Call<ResponseData> saveOrder(@Body RequestBody params);

    @POST("getOrderHistory")
    @FormUrlEncoded
    Call<OrderHistory> getOrderHistory(@Field("user_id") int user_id, @Field("room_number") int room_number);

    @POST("qrControl")
    Call<ResponseData> qrControl(@Body RequestBody params);

    @POST("MHlogin")
    @FormUrlEncoded
    Call<LoginUserAfter> loginAfterPrivateKey(@Field("qr_key") String qr_key, @Field("private_key") String private_key);
}
