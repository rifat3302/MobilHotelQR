package com.example.mobilhotelqr.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobilhotelqr.R;

public class FragmentHospital extends Fragment {
    private View mViev;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViev = inflater.inflate(R.layout.fragment_hospital,container,false);
        return mViev;





    }
}
