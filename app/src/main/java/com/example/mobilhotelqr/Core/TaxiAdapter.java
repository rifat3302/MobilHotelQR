package com.example.mobilhotelqr.Core;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.PojoModels.Taxi.TaxiResult;
import com.example.mobilhotelqr.R;


public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;

    public TextView textView;
    private TaxiResult taxiResultResponse;

    private int count = 0;

    public TaxiAdapter(Context mContext, TaxiResult taxiResultResponse) {
        this.mContext = mContext;
        this.taxiResultResponse = taxiResultResponse;
        this.count = taxiResultResponse.getTaxi().size();
    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_tasarim_taxi,parent,false);
        return new TaxiAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {
        textView.setText(taxiResultResponse.getTaxi().get(position).getName());
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent (Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+taxiResultResponse.getTaxi().get(position).getFormattedPhoneNumber()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                Toast.makeText(mContext,"deneme",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.count;
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public ImageButton imageButton;

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView9);
            imageButton = itemView.findViewById(R.id.imageButton);

        }
    }
}
