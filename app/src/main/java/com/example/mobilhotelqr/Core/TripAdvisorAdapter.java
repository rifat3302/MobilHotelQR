package com.example.mobilhotelqr.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.PojoModels.GooglePlaces.GooglePlaces;
import com.example.mobilhotelqr.R;

public class TripAdvisorAdapter extends RecyclerView.Adapter<TripAdvisorAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;
    private GooglePlaces googlePlaces;

    public TripAdvisorAdapter( GooglePlaces googlePlaces, Context mContext) {
        this.mContext = mContext;
        this.googlePlaces = googlePlaces;
    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.tripadvisor_card_tasarim,parent,false);
        return new TripAdvisorAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {
        if(googlePlaces.getPlaces().get(position).getType()==2){
            holder.textView.setText(googlePlaces.getPlaces().get(position).getNameHead().toString());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Todo burada yeni fragmente yönlenecek
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return googlePlaces.getPlaces().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public LinearLayout button;
        public TextView textView;
        //buraya card tasarımm üzerindeki objeler tanımlannır.

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.tripadvisor_lineer);
            textView = itemView.findViewById(R.id.tripadvisor_text);
            //buraya card tasarımm üzerindeki objeler yerleştirilir.

        }
    }
}
