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

public class PharmAdapter extends RecyclerView.Adapter<PharmAdapter.CardViewTasatimNesneleriniTutucu> {

    public Place pharmacyDutyList;
    public Context context;


    public PharmAdapter(Place  pharmacyDutyList, Context context) {
        this.pharmacyDutyList = pharmacyDutyList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.pharm_card_tasarim,parent,false);
        return new PharmAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {

        holder.textView.setText(pharmacyDutyList.getData().get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("geo:"+pharmacyDutyList.getData().get(position).getLat()+", "+pharmacyDutyList.getData().get(position).getLon()+"?q="+
               pharmacyDutyList.getData().get(position).getLat()+", "+pharmacyDutyList.getData().get(position).getLon()+"("+pharmacyDutyList.getData().get(position).getName()+")");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pharmacyDutyList.getData().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public ImageView button;
        public TextView textView;
        //buraya card tasarımm üzerindeki objeler tanımlannır.

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.imageView15);
            textView = itemView.findViewById(R.id.textView12);
            //buraya card tasarımm üzerindeki objeler yerleştirilir.

        }
    }


}
