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

import com.example.mobilhotelqr.Core.HospitalAdapter;
import com.example.mobilhotelqr.Core.PharmAdapter;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

public class FragmentHospital extends Fragment {
    private View mViev;

    private RecyclerView rvHospital ;
    private HospitalAdapter hospitalAdapter;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViev = inflater.inflate(R.layout.fragment_hospital,container,false);
        this.getHospital(mViev);
        return mViev;
    }

    public void getHospital(View mViews){

        mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("GooglePlaces", "");
        Gson gson = new Gson();
        GooglePlaces hospital = gson.fromJson(json,GooglePlaces.class);
        rvHospital = mViews.findViewById(R.id.rv_hospital);
        rvHospital.setHasFixedSize(true);
        rvHospital.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        hospitalAdapter = new HospitalAdapter(hospital.getPlaces().get(0),getActivity().getBaseContext());
        rvHospital.setAdapter(hospitalAdapter);
    }
}
