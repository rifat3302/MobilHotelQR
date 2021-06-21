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
import com.example.mobilhotelqr.Core.PharmAdapter;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;


public class FragmentPharmacy extends Fragment {
    private View mViev;

    private RecyclerView rvPharm ;
    private PharmAdapter pharmAdapter;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViev = inflater.inflate(R.layout.fragment_pharmacy,container,false);
        this.getPharm(mViev);
        return mViev;
    }

    public void getPharm(View mViews){

        mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("GooglePlaces", "");


            Gson gson = new Gson();
            GooglePlaces pharm = gson.fromJson(json,GooglePlaces.class);
            rvPharm = mViews.findViewById(R.id.rvPharm);
            rvPharm.setHasFixedSize(true);
            rvPharm.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            pharmAdapter = new PharmAdapter(pharm.getPlaces().get(1),getActivity().getBaseContext());
            rvPharm.setAdapter(pharmAdapter);
    }
}
