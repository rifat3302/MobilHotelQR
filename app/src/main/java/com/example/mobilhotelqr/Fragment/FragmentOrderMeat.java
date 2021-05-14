package com.example.mobilhotelqr.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobilhotelqr.Core.OrderAdapterMeat;
import com.example.mobilhotelqr.PojoModels.Menu.Meat;
import com.example.mobilhotelqr.PojoModels.Menu.MenuData;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

import java.util.List;

public class FragmentOrderMeat extends Fragment {

    private RecyclerView rvMeat ;
    private List<Meat> meatArrayList;
    private OrderAdapterMeat orderAdapterMeat;
    private View mViev;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViev = inflater.inflate(R.layout.fragment_order_meat,container,false);

        mPrefs = this.getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Menu", "");
        MenuData menuData = gson.fromJson(json, MenuData.class);
        if(json==null){
            Toast.makeText(getActivity().getBaseContext(),"Meat  Fragment  data is null",Toast.LENGTH_SHORT).show();
        }else{

            meatArrayList=menuData.getMeat();

            rvMeat = mViev.findViewById(R.id.rvSnack);
            rvMeat.setHasFixedSize(true);
            rvMeat.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            orderAdapterMeat = new OrderAdapterMeat(getActivity().getBaseContext(),meatArrayList);
            rvMeat.setAdapter(orderAdapterMeat);
        }


        return  mViev;
    }
}
