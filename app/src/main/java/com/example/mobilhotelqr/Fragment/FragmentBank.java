package com.example.mobilhotelqr.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobilhotelqr.Core.BankAdapter;
import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

public class FragmentBank extends Fragment {
    private View mViev;
    private RecyclerView rvBank ;
    private BankAdapter bankAdapter;
    SharedPreferences mPrefs ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mViev = inflater.inflate(R.layout.fragment_bank,container,false);
        this.getBanks(mViev);
        return mViev;

    }

    public void getBanks(View mViews){

        mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String json = mPrefs.getString("GooglePlaces", "");


        Gson gson = new Gson();
        GooglePlaces banks = gson.fromJson(json,GooglePlaces.class);
        rvBank = mViews.findViewById(R.id.rv_banks);
        rvBank.setHasFixedSize(true);
        rvBank.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        bankAdapter = new BankAdapter(banks.getPlaces().get(3),getActivity().getBaseContext());
        rvBank.setAdapter(bankAdapter);
    }


}
