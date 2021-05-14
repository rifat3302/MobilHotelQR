package com.example.mobilhotelqr.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.PojoModels.Menu.Meat;
import com.example.mobilhotelqr.PojoModels.Menu.Snack;
import com.example.mobilhotelqr.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.mobilhotelqr.Constant.IMAGE_URL;

public class OrderAdapterSnack extends RecyclerView.Adapter<OrderAdapterSnack.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;
    private List<Snack> menuSnackData;

    public OrderAdapterSnack(Context mContext, List<Snack> menuSnackData) {
        this.mContext = mContext;
        this.menuSnackData = menuSnackData;
    }





    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {

        public ImageView imageViewOrder;
        public TextView textViewOrderName;
        public TextView textViewOrderFiyat;
        public Button buttonSatınAl;


        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            imageViewOrder = itemView.findViewById(R.id.imageViewOrder);
            textViewOrderName = itemView.findViewById(R.id.textViewOrderName);
            textViewOrderFiyat = itemView.findViewById(R.id.textViewOrderFiyat);
            buttonSatınAl = itemView.findViewById(R.id.buttonSatınAl);

        }
    }

    @NonNull
    @Override
    public OrderAdapterSnack.CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_order_tasarim,parent,false);
        return new OrderAdapterSnack.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterSnack.CardViewTasatimNesneleriniTutucu holder, int position) {

        Snack menuDataSingle = menuSnackData.get(position);
        holder.textViewOrderName.setText(menuDataSingle.getName());
        holder.textViewOrderFiyat.setText(menuDataSingle.getPrice().toString());
        Picasso.get().load(IMAGE_URL+menuDataSingle.getImageUrl()).into(holder.imageViewOrder);


        holder.buttonSatınAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,menuDataSingle.getName()+" sepete  eklendi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuSnackData.size();

    }
}
