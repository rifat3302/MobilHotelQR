package com.example.mobilhotelqr;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private RetrofitProcess retrofitProcess;

    private View mView;

    private TextView textViewPubCa,textViewPubCo,textViewSaunaCo,textViewRestaurantCo,textViewGymCo,textViewOtelCo;

    SharedPreferences mPrefs ;


    public void allOccupancy() throws IOException {
        retrofitProcess = ApiUtils.getOccupancy();
        retrofitProcess.allDashboarOccupancy().enqueue(new Callback<OccupancyData>() {
            @Override
            public void onResponse(Call<OccupancyData> call, retrofit2.Response<OccupancyData> response) {

               mPrefs = getActivity().getSharedPreferences("MobilHotelInfo" ,  Context.MODE_PRIVATE);
               mPrefs.edit().remove("Occupancy").commit();

                OccupancyData mData = response.body();
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(mData);
                prefsEditor.putString("Occupancy", json);
                prefsEditor.commit();


            }

            @Override
            public void onFailure(Call<OccupancyData> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dashboard,container,false);

        mPrefs = this.getActivity().getSharedPreferences("MobilHotelInfo",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Occupancy", "");
        OccupancyData occupancyData = gson.fromJson(json, OccupancyData.class);

        if(occupancyData!=null){

            final int[] MY_COLORS = {
                    Color.  rgb(245, 124, 0),
                    Color. rgb(249, 168, 37)
            };

            ArrayList<Integer> colors = new ArrayList<>();


            for(int c: MY_COLORS) colors.add(c);

            PieChart  pieChartPool = mView.findViewById(R.id.pieChartPool);
            PieChart  pieChartPub = mView.findViewById(R.id.pieChartPub);
            PieChart  pieChartSauna = mView.findViewById(R.id.pieChartSauna);
            PieChart  pieChartRestaurant = mView.findViewById(R.id.pieChartRestaurant);
            PieChart  pieChartGym = mView.findViewById(R.id.pieChartGym);
            PieChart  pieChartHotel = mView.findViewById(R.id.pieChartOtel);

            //POOL
            pieChartPool.setUsePercentValues(true);
            List<PieEntry> valuePool = new ArrayList<>();
            valuePool.add(new PieEntry(occupancyData.getData().getPool().getPercent()));
            valuePool.add(new PieEntry(100-occupancyData.getData().getPool().getPercent()));

            PieDataSet pieDataSetPool = new PieDataSet(valuePool,"Pool");
            pieChartPool.setCenterText("Pool" );
            pieChartPool.setCenterTextSize(14f);
            pieChartPool.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
            PieData pieDataPool = new PieData(pieDataSetPool);
            pieChartPool.getDescription().setEnabled(false);
            pieChartPool.getLegend().setEnabled(false);
            pieChartPool.setData(pieDataPool);
            pieDataSetPool.setColors(colors);
            pieChartPool.animateXY(1400,1400);
            textViewPubCo = (TextView) mView.findViewById(R.id.textViewPubCo);
            textViewPubCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
            textViewPubCo.setText("Availability "+occupancyData.getData().getPool().getCa()+" | "+occupancyData.getData().getPool().getCo());



            //PUB
            pieChartPub.setUsePercentValues(true);
            pieChartPub.setCenterText("Pub" );
            pieChartPub.setCenterTextSize(14f);
            pieChartPub.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
            List<PieEntry> valuePub = new ArrayList<>();
            valuePub.add(new PieEntry(occupancyData.getData().getPub().getPercent()));
            valuePub.add(new PieEntry(100-occupancyData.getData().getPub().getPercent()));

            PieDataSet pieDataSetPub = new PieDataSet(valuePub,"Pub");
            PieData pieDataPub = new PieData(pieDataSetPub);
            pieChartPub.getDescription().setEnabled(false);
            pieChartPub.getLegend().setEnabled(false);
            pieChartPub.setData(pieDataPub);
            pieDataSetPub.setColors(colors);
            textViewPubCa = (TextView) mView.findViewById(R.id.textViewPubCa);
            textViewPubCa.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
            textViewPubCa.setText("Availability "+occupancyData.getData().getPub().getCa()+" | "+occupancyData.getData().getPub().getCo());
            pieChartPub.animateXY(1400,1400);

            //Sauna
            pieChartSauna.setUsePercentValues(true);
            pieChartSauna.setCenterText("Sauna" );
            pieChartSauna.setCenterTextSize(14f);
            pieChartSauna.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
            List<PieEntry> valueSauna = new ArrayList<>();
            valueSauna.add(new PieEntry(occupancyData.getData().getSauna().getPercent()));
            valueSauna.add(new PieEntry(100-occupancyData.getData().getSauna().getPercent()));

            PieDataSet pieDataSetSauna = new PieDataSet(valueSauna,"Sauna");
            PieData pieDataSauna = new PieData(pieDataSetSauna);
            pieChartSauna.getDescription().setEnabled(false);
            pieChartSauna.getLegend().setEnabled(false);
            pieChartSauna.setData(pieDataSauna);
            pieDataSetSauna.setColors(colors);
            pieChartSauna.animateXY(1400,1400);
            textViewSaunaCo = (TextView) mView.findViewById(R.id.textViewSauna);
            textViewSaunaCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
            textViewSaunaCo.setText("Availability "+occupancyData.getData().getSauna().getCa()+" | "+occupancyData.getData().getSauna().getCo());


            //Restaurant
            pieChartRestaurant.setUsePercentValues(true);
            pieChartRestaurant.setCenterText("Restaurant" );
            pieChartRestaurant.setCenterTextSize(14f);
            pieChartRestaurant.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
            List<PieEntry> valueRestaurant = new ArrayList<>();
            valueRestaurant.add(new PieEntry(occupancyData.getData().getRestaurant().getPercent()));
            valueRestaurant.add(new PieEntry(100-occupancyData.getData().getRestaurant().getPercent()));

            PieDataSet pieDataSetRestaurant = new PieDataSet(valueRestaurant,"Restaurant");
            PieData pieDataRestaurant = new PieData(pieDataSetRestaurant);
            pieChartRestaurant.getDescription().setEnabled(false);
            pieChartRestaurant.getLegend().setEnabled(false);
            pieChartRestaurant.setData(pieDataRestaurant);
            pieDataSetRestaurant.setColors(colors);
            pieChartRestaurant.animateXY(1400,1400);

            textViewRestaurantCo = (TextView) mView.findViewById(R.id.textViewRestaurant);
            textViewRestaurantCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
            textViewRestaurantCo.setText("Availability "+occupancyData.getData().getRestaurant().getCa()+" | "+occupancyData.getData().getRestaurant().getCo());

            //Gym
            pieChartGym.setUsePercentValues(true);
            pieChartGym.setCenterText("Gym" );
            pieChartGym.setCenterTextSize(14f);
            pieChartGym.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
            List<PieEntry> valueGym = new ArrayList<>();
            valueGym.add(new PieEntry(occupancyData.getData().getGym().getPercent()));
            valueGym.add(new PieEntry(100-occupancyData.getData().getGym().getPercent()));

            PieDataSet pieDataSetGym = new PieDataSet(valueGym,"Gym");
            PieData pieDataGym = new PieData(pieDataSetGym);
            pieChartGym.getDescription().setEnabled(false);
            pieChartGym.getLegend().setEnabled(false);
            pieChartGym.setData(pieDataGym);
            pieDataSetGym.setColors(colors);
            pieChartGym.animateXY(1400,1400);
            textViewGymCo = (TextView) mView.findViewById(R.id.textViewGym);
            textViewGymCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
            textViewGymCo.setText("Availability "+occupancyData.getData().getGym().getCa()+" | "+occupancyData.getData().getGym().getCo());

            //Hotel
            pieChartHotel.setUsePercentValues(true);
            pieChartHotel.setCenterText("Hotel" );
            pieChartHotel.setCenterTextSize(14f);
            pieChartHotel.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
            List<PieEntry> valueHotel = new ArrayList<>();
            valueHotel.add(new PieEntry(occupancyData.getData().getHotel().getPercent()));
            valueHotel.add(new PieEntry(100-occupancyData.getData().getHotel().getPercent()));

            PieDataSet pieDataSetHotel = new PieDataSet(valueHotel,"Hotel");
            PieData pieDataHotel = new PieData(pieDataSetHotel);
            pieChartHotel.getDescription().setEnabled(false);
            pieChartHotel.getLegend().setEnabled(false);
            pieChartHotel.setData(pieDataHotel);
            pieDataSetHotel.setColors(colors);
            pieChartHotel.animateXY(1400,1400);
            textViewOtelCo = (TextView) mView.findViewById(R.id.textViewOtel);
            textViewOtelCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
            textViewOtelCo.setText("Availability "+occupancyData.getData().getHotel().getCa()+" | "+occupancyData.getData().getHotel().getCo());

        }






        try {
            allOccupancy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mView;


    }

}
