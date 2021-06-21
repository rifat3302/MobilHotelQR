package com.example.mobilhotelqr.Core;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.PojoModels.GooglePlaces.Place;
import com.example.mobilhotelqr.R;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.CardViewTasatimNesneleriniTutucu> {

    public Place hospitalList;
    public Context context;

    public HospitalAdapter(Place hospitalList, Context context) {
        this.hospitalList = hospitalList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.hospital_card_tasarim,parent,false);
        return new HospitalAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {
        holder.textView.setText(hospitalList.getData().get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("geo:"+hospitalList.getData().get(position).getLat()+", "+hospitalList.getData().get(position).getLon()+"?q="+
                        hospitalList.getData().get(position).getLon()+", "+hospitalList.getData().get(position).getLat()+"("+hospitalList.getData().get(position).getName()+")");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.getData().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public ImageView button;
        public TextView textView;
        //buraya card tasarımm üzerindeki objeler tanımlannır.

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.hospitalnav);
            textView = itemView.findViewById(R.id.hospital_text);
            //buraya card tasarımm üzerindeki objeler yerleştirilir.

        }
    }
}
