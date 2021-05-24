package com.example.mobilhotelqr.Core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.Models.Order;
import com.example.mobilhotelqr.PojoModels.OrderHistory.OrderDetail;
import com.example.mobilhotelqr.PojoModels.OrderHistory.OrderHistory;
import com.example.mobilhotelqr.R;

import java.util.List;

import retrofit2.Response;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;

    private Response<OrderHistory> orders;

    private int count = 0;

    public LinearLayout linearLayoutname,linearLayoutCount,linearLayoutTutar;
    public TextView baslik,tutarText,textState;
    public Button button;
    public ImageView imageView;

    public OrderHistoryAdapter(Context mContext, Response<OrderHistory> orders, View mView, FragmentManager fm) {
        this.mContext = mContext;
        this.orders = orders;
        this.count = orders.body().getData().size();
    }

    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {


        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            linearLayoutname = itemView.findViewById(R.id.orderName);
            linearLayoutCount = itemView.findViewById(R.id.orderCount);
            linearLayoutTutar = itemView.findViewById(R.id.orderTotal);
            baslik = itemView.findViewById(R.id.baslik);
            tutarText = itemView.findViewById(R.id.textView17);
            textState = itemView.findViewById(R.id.textView22);
            imageView = itemView.findViewById(R.id.imageView3);

        }
    }
    @NonNull
    @Override
    public OrderHistoryAdapter.CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_order_history_card,parent,false);
        return new OrderHistoryAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {


        baslik.setText(orders.body().getData().get(position).getOrder().getOrderDate());
        baslik.setTypeface(baslik.getTypeface(), Typeface.BOLD);
        tutarText.setText(orders.body().getData().get(position).getOrder().getTotal().toString()+" ₺");

        if(orders.body().getData().get(position).getOrder().getState()==1){
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hourglass, mContext.getApplicationContext().getTheme()));
            textState.setText("Ordered");
            textState.setTextColor(mContext.getResources().getColor(R.color.ordered));
        }
        if(orders.body().getData().get(position).getOrder().getState()==2){
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cooking, mContext.getApplicationContext().getTheme()));
            textState.setText("Getting Ready");
            textState.setTextColor(mContext.getResources().getColor(R.color.reading));
        }
        if(orders.body().getData().get(position).getOrder().getState()==3){
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.chef, mContext.getApplicationContext().getTheme()));
            textState.setText("Ready");
            textState.setTextColor(mContext.getResources().getColor(R.color.ready));
        }
        if(orders.body().getData().get(position).getOrder().getState()==4){
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cancel, mContext.getApplicationContext().getTheme()));
            textState.setText("Canceled");
            textState.setTextColor(mContext.getResources().getColor(R.color.canceled));
        }

        //TODO zaman kalırsa kuru firebaseden okuyacak hale getir.
       // tutarText.setText(orders.body().getData().get(position).getOrder().getTotal().toString()+" ₺");

        for (int i =0 ; i<orders.body().getData().get(position).getOrderDetails().size();i++){
            TextView tvName = new TextView(this.mContext);
            tvName.setText(orders.body().getData().get(position).getOrderDetails().get(i).getName());
            tvName.setId(orders.body().getData().get(position).getOrderDetails().get(i).getOrderUserId());

            TextView tvCount = new TextView(this.mContext);
            tvCount.setText(orders.body().getData().get(position).getOrderDetails().get(i).getCount().toString());
            tvCount.setId(orders.body().getData().get(position).getOrderDetails().get(i).getOrderUserId()*i);

            TextView tvTutar = new TextView(this.mContext);
            tvTutar.setText(orders.body().getData().get(position).getOrderDetails().get(i).getTotal().toString());
            tvTutar.setId(orders.body().getData().get(position).getOrderDetails().get(i).getOrderUserId()*i*2);

            linearLayoutname.addView(tvName);
            linearLayoutCount.addView(tvCount);
            linearLayoutTutar.addView(tvTutar);

        }

    }

    @Override
    public int getItemCount() {
        return count;
    }


}
