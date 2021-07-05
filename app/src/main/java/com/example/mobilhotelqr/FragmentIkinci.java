package com.example.mobilhotelqr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.PojoModels.RoomInfo.RoomInfoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentIkinci extends Fragment {

    private View mView;
    private TextView textView35,textView36;
    private RetrofitProcess retrofitProcess;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_nav_layout_ikinci,container,false);
        setContent(mView);
        return  mView;
    }

    public void setContent(View view) {
        textView35 = view.findViewById(R.id.textView35);
        textView36 = view.findViewById(R.id.textView36);

        retrofitProcess = ApiUtils.getRoomInfo();
        retrofitProcess.getRoomInfo().enqueue(new Callback<RoomInfoModel>() {
            @Override
            public void onResponse(Call<RoomInfoModel> call, Response<RoomInfoModel> response) {

                textView35.setText(response.body().getRoomInfo().get(0).getAddress());
                textView36.setText(response.body().getRoomInfo().get(0).getWifiPassword());


                int a = 0;
            }

            @Override
            public void onFailure(Call<RoomInfoModel> call, Throwable t) {
                int b = 0 ;
            }
        });
    }
}
