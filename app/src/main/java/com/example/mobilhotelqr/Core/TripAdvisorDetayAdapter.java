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
import com.squareup.picasso.Picasso;


public class TripAdvisorDetayAdapter  extends RecyclerView.Adapter<TripAdvisorDetayAdapter.CardViewTasatimNesneleriniTutucu> {

    private Place googlePlaces;
    private Context mContext;
    public TripAdvisorDetayAdapter(Place googlePlaces, Context context) {
        this.googlePlaces = googlePlaces;
        this.mContext = context;

    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.tripadvisordetay_card_tasarim,parent,false);
        return new TripAdvisorDetayAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {

        Picasso.get().load(googlePlaces.getData().get(position).getImageUrl()).into(holder.imageView);
        holder.textView.setText(googlePlaces.getData().get(position).getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("geo:"+googlePlaces.getData().get(position).getGeo().getLat()+", "+googlePlaces.getData().get(position).getGeo().getLng()+"?q="+
                        googlePlaces.getData().get(position).getGeo().getLat()+", "+googlePlaces.getData().get(position).getGeo().getLng()+"("+googlePlaces.getData().get(position).getName()+")");
                Intent intent =new Intent(Intent.ACTION_VIEW,uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return googlePlaces.getData().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public ImageView button;
        public TextView textView;
        public ImageView imageView;
        //buraya card tasarımm üzerindeki objeler tanımlannır.

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.tripadvisordetay_nav);
            textView = itemView.findViewById(R.id.tripadvisordetay_text);
            imageView = itemView.findViewById(R.id.imageView14de);
            //buraya card tasarımm üzerindeki objeler yerleştirilir.

        }
    }
}
