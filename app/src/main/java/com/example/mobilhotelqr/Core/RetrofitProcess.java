package com.example.mobilhotelqr.Core;

import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitProcess {

    @GET("mobilDashboardOccupancy")
    Call<OccupancyData> allDashboarOccupancy();

    @GET("mobilMenu")
    Call<MenuData> getAllMenu();
}
