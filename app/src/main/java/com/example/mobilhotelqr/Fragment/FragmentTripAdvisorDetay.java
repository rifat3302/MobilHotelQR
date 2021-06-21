package com.example.mobilhotelqr.Fragment;

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
import com.example.mobilhotelqr.Core.TripAdvisorDetayAdapter;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.Place;
import com.example.mobilhotelqr.R;

import java.util.List;

public class FragmentTripAdvisorDetay extends Fragment {

    private Place place;

    private RecyclerView rv_tripadvisor_detay ;
    private TripAdvisorDetayAdapter tripAdvisorDetayAdapter;
    private List<Place> googlePlaces;


    public FragmentTripAdvisorDetay(Place place) {
        this.place = place;
    }

    View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tripadvisor_detay,container,false);

        rv_tripadvisor_detay = mView.findViewById(R.id.rv_tripadvisor_detay);
        rv_tripadvisor_detay.setHasFixedSize(true);
        rv_tripadvisor_detay.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        tripAdvisorDetayAdapter = new TripAdvisorDetayAdapter(place,getContext());
        rv_tripadvisor_detay.setAdapter(tripAdvisorDetayAdapter);

        return  mView;
    }
}
