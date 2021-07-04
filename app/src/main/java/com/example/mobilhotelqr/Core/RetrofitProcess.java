package com.example.mobilhotelqr.Core;

import com.example.mobilhotelqr.PojoModels.AlarmResponse;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.LogoutControl;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.example.mobilhotelqr.PojoModels.OrderHistory.OrderHistory;
import com.example.mobilhotelqr.PojoModels.QrPlacesControl;
import com.example.mobilhotelqr.PojoModels.Response.ResponseData;
import com.example.mobilhotelqr.PojoModels.Taxi.TaxiResult;

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

    @POST("logoutControlServiceForMobile")
    @FormUrlEncoded
    Call<LogoutControl> logoutControlServiceForMobile(@Field("user_id")  int user_id);

    @GET("getTaxi")
    Call<TaxiResult> getTaxi();

    @POST("controlPlacesQrKey")
    @FormUrlEncoded
    Call<QrPlacesControl> qrPlacesControl(@Field("user_id")  int user_id, @Field("qr_key")  String qr_key);

    @GET("getPlaces")
    Call<GooglePlaces> getGooglePlaces();

    @POST("setAlarm")
    @FormUrlEncoded
    Call<AlarmResponse> setAlarm(@Field("date") String wake_up_date,@Field("room_number") int room_number);


}
