package com.example.mobilhotelqr.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.OrderHistoryAdapter;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.Core.TaxiAdapter;
import com.example.mobilhotelqr.MainActivity;
import com.example.mobilhotelqr.NavigationActivity;
import com.example.mobilhotelqr.PojoModels.Taxi.TaxiResult;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTaxi extends Fragment {

    View mView;

    private RecyclerView rvTaxi ;
    private TaxiAdapter taxiAdapter;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_taxi,container,false);
        this.getTaxiInfo(mView);
        return  mView;
    }
    public void getTaxiInfo(View mViews){

        mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("Taxi", "");

        /**
         * Shared preferencesta varsa tekrar servise gitmesi önleniyor
         */
        if( json != ""){
            Gson gson = new Gson();
            TaxiResult taxi = gson.fromJson(json,TaxiResult.class);
            rvTaxi = mViews.findViewById(R.id.rv_taxi);
            rvTaxi.setHasFixedSize(true);
            rvTaxi.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            taxiAdapter = new TaxiAdapter(getActivity().getBaseContext(),taxi);
            rvTaxi.setAdapter(taxiAdapter);
        }else {
            retrofitProcess = ApiUtils.getTaxi();
            retrofitProcess.getTaxi().enqueue(new Callback<TaxiResult>() {
                @Override
                public void onResponse(Call<TaxiResult> call, Response<TaxiResult> response) {
                    //Todo Shared preferencese yaz
                    TaxiResult taxiResult = new TaxiResult() ;
                    taxiResult.setTaxi(response.body().getTaxi());
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(taxiResult);
                    prefsEditor.putString("Taxi", json);
                    prefsEditor.commit();
                    rvTaxi = mViews.findViewById(R.id.rv_taxi);
                    rvTaxi.setHasFixedSize(true);
                    rvTaxi.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                    taxiAdapter = new TaxiAdapter(getActivity().getBaseContext(),taxiResult);
                    rvTaxi.setAdapter(taxiAdapter);
                }

                @Override
                public void onFailure(Call<TaxiResult> call, Throwable t) {
                    int a = 0;

                }
            });
        }
    }
}
