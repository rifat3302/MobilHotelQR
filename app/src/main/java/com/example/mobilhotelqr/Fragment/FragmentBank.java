package com.example.mobilhotelqr.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobilhotelqr.R;

public class FragmentBank extends Fragment {
    private View mViev;
    Button btn_hospital;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mViev = inflater.inflate(R.layout.fragment_bank,container,false);
        btn_hospital=(Button)mViev.findViewById(R.id.btn_bank);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("geo:38.37028162087201, 27.20486304677734?z=15");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        return mViev;

    }


}
