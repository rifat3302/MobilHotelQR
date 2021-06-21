package com.example.mobilhotelqr.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilhotelqr.Fragment.FragmentTripAdvisorDetay;

import com.example.mobilhotelqr.PojoModels.GooglePlaces.Place;
import com.example.mobilhotelqr.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TripAdvisorAdapter extends RecyclerView.Adapter<TripAdvisorAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;
    private List<Place> googlePlaces;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    public TripAdvisorAdapter(List<Place> googlePlaces, Context mContext, FragmentManager fm) {
        this.mContext = mContext;
        this.googlePlaces = googlePlaces;
        this.fragmentManager = fm;

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
        if(googlePlaces.get(position).getType()==2){

            Picasso.get().load(googlePlaces.get(position).getData().get(0).getImageUrl()).into(holder.imageView);
            holder.textView.setText(googlePlaces.get(position).getNameHead().toString());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Place  place = googlePlaces.get(position);
                    fragment = new FragmentTripAdvisorDetay(place);
                    fragmentManager.beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return googlePlaces.size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public LinearLayout button;
        public TextView textView;
        public ImageView imageView;
        //buraya card tasarımm üzerindeki objeler tanımlannır.

        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.tripadvisor_lineer);
            textView = itemView.findViewById(R.id.tripadvisor_text);
            imageView = itemView.findViewById(R.id.imageView14);
            //buraya card tasarımm üzerindeki objeler yerleştirilir.

        }
    }
}
