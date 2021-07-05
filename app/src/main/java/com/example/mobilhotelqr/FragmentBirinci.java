package com.example.mobilhotelqr;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.mobilhotelqr.PojoModels.LoginUserAfter.LoginUserAfter;
import com.example.mobilhotelqr.PojoModels.UserInfo.UserInfoModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBirinci extends Fragment {

    private View mView;
    private TextView textView29,textView30,textView31,textView32,textView33,textView6;
    private RetrofitProcess retrofitProcess;
    SharedPreferences mPrefs ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_nav_layout_birinci,container,false);
        setContent(mView);
        return mView;
    }

    public void setContent(View view) {
        textView6 = view.findViewById(R.id.textView6);
        textView29 = view.findViewById(R.id.textView29);
        textView30 = view.findViewById(R.id.textView30);
        textView31 = view.findViewById(R.id.textView31);
        textView32 = view.findViewById(R.id.textView32);
        textView33 = view.findViewById(R.id.textView33);

        Gson gson = new Gson();
        mPrefs = getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        String jsonn = mPrefs.getString("User", "");
        LoginUserAfter user = gson.fromJson(jsonn,LoginUserAfter.class);

        retrofitProcess = ApiUtils.getUserInfo();
        retrofitProcess.getUserInfo(user.getData().getUser().getId()).enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {

                textView30.setText(response.body().getUserInfo().getName());
                textView6.setText(response.body().getUserInfo().getRoomNumber().toString());
                textView31.setText(response.body().getUserInfo().getSurname());
                textView32.setText(response.body().getUserInfo().getDate().toString());
                textView33.setText(response.body().getUserInfo().getTotal().toString());
                textView29.setText(response.body().getUserInfo().getExitDate().toString());
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {
                int b = 0;
            }
        });
    }
}
