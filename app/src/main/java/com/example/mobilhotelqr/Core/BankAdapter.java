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

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.CardViewTasatimNesneleriniTutucu> {

    public Place bankList;
    public Context context;

    public BankAdapter(Place  bankList, Context context) {
        this.bankList = bankList;
        this.context = context;
    }


    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.bank_card_tasarim,parent,false);
        return new BankAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {

        holder.textView.setText(bankList.getData().get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("geo:"+bankList.getData().get(position).getLat()+", "+bankList.getData().get(position).getLon()+"?q="+
                        bankList.getData().get(position).getLat()+", "+bankList.getData().get(position).getLon()+"("+bankList.getData().get(position).getName()+")");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankList.getData().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public ImageView button;
        public TextView textView;
        //buraya card tasarımm üzerindeki objeler tanımlannır.

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.banknav);
            textView = itemView.findViewById(R.id.banknav_text);
            //buraya card tasarımm üzerindeki objeler yerleştirilir.

        }
    }
}
