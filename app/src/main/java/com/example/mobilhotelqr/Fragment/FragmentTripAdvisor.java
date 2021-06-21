package com.example.mobilhotelqr.Fragment;

import android.content.Context;
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
import com.example.mobilhotelqr.Core.TripAdvisorAdapter;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

public class FragmentTripAdvisor extends Fragment {

    View mView;
    private RecyclerView rv_tripadvisor ;
    private TripAdvisorAdapter tripAdvisorAdapter;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_tripadvisor,container,false);
        this.getGooglePlaces(mView);
        return  mView;
    }

    public void getGooglePlaces(View mViews){

        mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("GooglePlaces", "");

        Gson gson = new Gson();
        GooglePlaces tripAdvisor = gson.fromJson(json,GooglePlaces.class);
        rv_tripadvisor = mViews.findViewById(R.id.rv_tripadvisor);
        rv_tripadvisor.setHasFixedSize(true);
        rv_tripadvisor.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        tripAdvisorAdapter = new TripAdvisorAdapter(tripAdvisor,getActivity().getBaseContext());
        rv_tripadvisor.setAdapter(tripAdvisorAdapter);
    }
}
