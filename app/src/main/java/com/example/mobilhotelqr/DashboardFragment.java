package com.example.mobilhotelqr;

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
import com.example.mobilhotelqr.PojoModels.Occupancy.OccupancyData;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

    public void allOccupancy() throws IOException {
        retrofitProcess = ApiUtils.getOccupancy();
        retrofitProcess.allDashboarOccupancy().enqueue(new Callback<OccupancyData>() {
            @Override
            public void onResponse(Call<OccupancyData> call, retrofit2.Response<OccupancyData> response) {

                createPieChart(response);

            }

            @Override
            public void onFailure(Call<OccupancyData> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_first_layout,container,false);

        final int[] MY_COLORS = {
                Color.  rgb(255, 255, 255),
        };
        ArrayList<Integer> colors = new ArrayList<>();

        for(int c: MY_COLORS) colors.add(c);

        PieChart  pieChartPool = mView.findViewById(R.id.pieChartPool);
        PieChart  pieChartPub = mView.findViewById(R.id.pieChartPub);
        PieChart  pieChartSauna = mView.findViewById(R.id.pieChartSauna);
        PieChart  pieChartRestaurant = mView.findViewById(R.id.pieChartRestaurant);
        PieChart  pieChartGym = mView.findViewById(R.id.pieChartGym);
        PieChart  pieChartOtel = mView.findViewById(R.id.pieChartOtel);

        pieChartPool.setUsePercentValues(true);
        pieChartPool.getDescription().setEnabled(false);
        pieChartPool.getLegend().setEnabled(false);
        List<PieEntry> valuePool = new ArrayList<>();
        valuePool.add(new PieEntry(100));
        PieDataSet pieDataSetPool = new PieDataSet(valuePool,"Pool");
        PieData pieDataPool = new PieData(pieDataSetPool);
        pieChartPool.setData(pieDataPool);
        pieDataSetPool.setColors(colors);


        pieChartPub.setUsePercentValues(true);
        pieChartPub.getDescription().setEnabled(false);
        pieChartPub.getLegend().setEnabled(false);
        List<PieEntry> valuePub = new ArrayList<>();
        valuePub.add(new PieEntry(100));
        PieDataSet pieDataSetPub = new PieDataSet(valuePub,"Pub");
        PieData pieDataPub = new PieData(pieDataSetPub);
        pieChartPub.setData(pieDataPub);
        pieDataSetPub.setColors(colors);
        textViewPubCo = (TextView) mView.findViewById(R.id.textViewPubCo);
        textViewPubCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
        textViewPubCo.setText("Availability");

        textViewPubCa = (TextView) mView.findViewById(R.id.textViewPubCa);
        textViewPubCa.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
        textViewPubCa.setText("Availability");

        //Sauna
        pieChartSauna.setUsePercentValues(true);
        pieChartSauna.getDescription().setEnabled(false);
        pieChartSauna.getLegend().setEnabled(false);
        List<PieEntry> valueSauna = new ArrayList<>();
        valueSauna.add(new PieEntry(100));
        PieDataSet pieDataSetSauna = new PieDataSet(valueSauna,"Sauna");
        PieData pieDataSauna = new PieData(pieDataSetSauna);
        pieChartSauna.setData(pieDataSauna);
        pieDataSetSauna.setColors(colors);
        textViewSaunaCo = (TextView) mView.findViewById(R.id.textViewSauna);
        textViewSaunaCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
        textViewSaunaCo.setText("Availability");

        //Restaurant
        pieChartRestaurant.setUsePercentValues(true);
        pieChartRestaurant.getDescription().setEnabled(false);
        pieChartRestaurant.getLegend().setEnabled(false);
        List<PieEntry> valueRestaurant = new ArrayList<>();
        valueRestaurant.add(new PieEntry(100));
        PieDataSet pieDataSetRestaurant = new PieDataSet(valueRestaurant,"Restaurant");
        PieData pieDataRestaurant = new PieData(pieDataSetRestaurant);
        pieChartRestaurant.setData(pieDataRestaurant);
        pieDataSetRestaurant.setColors(colors);
        textViewRestaurantCo = (TextView) mView.findViewById(R.id.textViewRestaurant);
        textViewRestaurantCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
        textViewRestaurantCo.setText("Availability");

        //GYM
        pieChartGym.setUsePercentValues(true);
        pieChartGym.getDescription().setEnabled(false);
        pieChartGym.getLegend().setEnabled(false);
        List<PieEntry> valueGym = new ArrayList<>();
        valueGym.add(new PieEntry(100));
        PieDataSet pieDataSetGym = new PieDataSet(valueGym,"Gym");
        PieData pieDataGym = new PieData(pieDataSetGym);
        pieChartGym.setData(pieDataGym);
        pieDataSetGym.setColors(colors);
        textViewGymCo = (TextView) mView.findViewById(R.id.textViewGym);
        textViewGymCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
        textViewGymCo.setText("Availability");

        //Otel

        pieChartOtel.setUsePercentValues(true);
        pieChartOtel.getDescription().setEnabled(false);
        pieChartOtel.getLegend().setEnabled(false);
        List<PieEntry> valueOtel = new ArrayList<>();
        valueOtel.add(new PieEntry(100));
        PieDataSet pieDataSetOtel = new PieDataSet(valueOtel,"Otel");
        PieData pieDataOtel = new PieData(pieDataSetOtel);
        pieChartOtel.setData(pieDataOtel);
        pieDataSetOtel.setColors(colors);
        textViewOtelCo = (TextView) mView.findViewById(R.id.textViewOtel);
        textViewOtelCo.setTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorPrimaryDark));
        textViewOtelCo.setText("Availability");



        try {
             allOccupancy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mView;


    }

    public void createPieChart(Response<OccupancyData> response){

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
        valuePool.add(new PieEntry(response.body().getData().getPool().getPercent()));
        valuePool.add(new PieEntry(100-response.body().getData().getPool().getPercent()));

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
        textViewPubCo.setText("Availability "+response.body().getData().getPool().getCa()+" | "+response.body().getData().getPool().getCo());



        //PUB
        pieChartPub.setUsePercentValues(true);
        pieChartPub.setCenterText("Pub" );
        pieChartPub.setCenterTextSize(14f);
        pieChartPub.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
        List<PieEntry> valuePub = new ArrayList<>();
        valuePub.add(new PieEntry(response.body().getData().getPub().getPercent()));
        valuePub.add(new PieEntry(100-response.body().getData().getPub().getPercent()));

        PieDataSet pieDataSetPub = new PieDataSet(valuePub,"Pub");
        PieData pieDataPub = new PieData(pieDataSetPub);
        pieChartPub.getDescription().setEnabled(false);
        pieChartPub.getLegend().setEnabled(false);
        pieChartPub.setData(pieDataPub);
        pieDataSetPub.setColors(colors);
        textViewPubCa.setText("Availability "+response.body().getData().getPub().getCa()+" | "+response.body().getData().getPub().getCo());
        pieChartPub.animateXY(1400,1400);

        //Sauna
        pieChartSauna.setUsePercentValues(true);
        pieChartSauna.setCenterText("Sauna" );
        pieChartSauna.setCenterTextSize(14f);
        pieChartSauna.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
        List<PieEntry> valueSauna = new ArrayList<>();
        valueSauna.add(new PieEntry(response.body().getData().getSauna().getPercent()));
        valueSauna.add(new PieEntry(100-response.body().getData().getSauna().getPercent()));

        PieDataSet pieDataSetSauna = new PieDataSet(valueSauna,"Sauna");
        PieData pieDataSauna = new PieData(pieDataSetSauna);
        pieChartSauna.getDescription().setEnabled(false);
        pieChartSauna.getLegend().setEnabled(false);
        pieChartSauna.setData(pieDataSauna);
        pieDataSetSauna.setColors(colors);
        pieChartSauna.animateXY(1400,1400);
        textViewSaunaCo.setText("Availability "+response.body().getData().getSauna().getCa()+" | "+response.body().getData().getSauna().getCo());


        //Restaurant
        pieChartRestaurant.setUsePercentValues(true);
        pieChartRestaurant.setCenterText("Restaurant" );
        pieChartRestaurant.setCenterTextSize(14f);
        pieChartRestaurant.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
        List<PieEntry> valueRestaurant = new ArrayList<>();
        valueRestaurant.add(new PieEntry(response.body().getData().getRestaurant().getPercent()));
        valueRestaurant.add(new PieEntry(100-response.body().getData().getRestaurant().getPercent()));

        PieDataSet pieDataSetRestaurant = new PieDataSet(valueRestaurant,"Restaurant");
        PieData pieDataRestaurant = new PieData(pieDataSetRestaurant);
        pieChartRestaurant.getDescription().setEnabled(false);
        pieChartRestaurant.getLegend().setEnabled(false);
        pieChartRestaurant.setData(pieDataRestaurant);
        pieDataSetRestaurant.setColors(colors);
        pieChartRestaurant.animateXY(1400,1400);
        textViewRestaurantCo.setText("Availability "+response.body().getData().getRestaurant().getCa()+" | "+response.body().getData().getRestaurant().getCo());

        //Gym
        pieChartGym.setUsePercentValues(true);
        pieChartGym.setCenterText("Gym" );
        pieChartGym.setCenterTextSize(14f);
        pieChartGym.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
        List<PieEntry> valueGym = new ArrayList<>();
        valueGym.add(new PieEntry(response.body().getData().getGym().getPercent()));
        valueGym.add(new PieEntry(100-response.body().getData().getGym().getPercent()));

        PieDataSet pieDataSetGym = new PieDataSet(valueGym,"Gym");
        PieData pieDataGym = new PieData(pieDataSetGym);
        pieChartGym.getDescription().setEnabled(false);
        pieChartGym.getLegend().setEnabled(false);
        pieChartGym.setData(pieDataGym);
        pieDataSetGym.setColors(colors);
        pieChartGym.animateXY(1400,1400);
        textViewGymCo.setText("Availability "+response.body().getData().getGym().getCa()+" | "+response.body().getData().getGym().getCo());

        //Hotel
        pieChartHotel.setUsePercentValues(true);
        pieChartHotel.setCenterText("Hotel" );
        pieChartHotel.setCenterTextSize(14f);
        pieChartHotel.setCenterTextColor(ContextCompat.getColor(getActivity().getBaseContext(),R.color.colorAccent));
        List<PieEntry> valueHotel = new ArrayList<>();
        valueHotel.add(new PieEntry(response.body().getData().getHotel().getPercent()));
        valueHotel.add(new PieEntry(100-response.body().getData().getHotel().getPercent()));

        PieDataSet pieDataSetHotel = new PieDataSet(valueHotel,"Hotel");
        PieData pieDataHotel = new PieData(pieDataSetHotel);
        pieChartHotel.getDescription().setEnabled(false);
        pieChartHotel.getLegend().setEnabled(false);
        pieChartHotel.setData(pieDataHotel);
        pieDataSetHotel.setColors(colors);
        pieChartHotel.animateXY(1400,1400);
        textViewOtelCo.setText("Availability "+response.body().getData().getHotel().getCa()+" | "+response.body().getData().getHotel().getCo());


    }
}
