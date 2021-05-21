package com.example.mobilhotelqr.Core;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.Models.Order;
import com.example.mobilhotelqr.PojoModels.Menu.Drink;
import com.example.mobilhotelqr.PojoModels.Menu.Meat;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.mobilhotelqr.Constant.IMAGE_URL;

public class OrderAdapterDrink extends RecyclerView.Adapter<OrderAdapterDrink.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;

    SharedPreferences mPrefs ;

    private List<Drink> menuDrinkData;

    public OrderAdapterDrink(Context mContext, List<Drink> menuDrinkData) {
        this.mContext = mContext;
        this.menuDrinkData = menuDrinkData;
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
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_order_tasarim,parent,false);
        return new CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {

        Drink menuDataSingle = menuDrinkData.get(position);
        holder.textViewOrderName.setText(menuDataSingle.getName());
        holder.textViewOrderFiyat.setText(menuDataSingle.getPrice().toString());
        Picasso.get().load(IMAGE_URL+menuDataSingle.getImageUrl()).into(holder.imageViewOrder);

        holder.buttonSatınAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPrefs = mContext.getSharedPreferences("MobilHotelInfo",Context.MODE_PRIVATE);
                Gson gsonKontrolOrder = new Gson();
                String jsonKontrolorder = mPrefs.getString("Order", "");
                if(jsonKontrolorder==""){
                    ArrayList<Order> orders = new ArrayList<>();
                    Order order = new Order();
                    order.setName(menuDataSingle.getName());
                    order.setMenu_id(menuDataSingle.getMenuId());
                    order.setId(menuDataSingle.getId());
                    order.setImg_url(menuDataSingle.getImageUrl());
                    order.setState(1);
                    order.setTotal(menuDataSingle.getPrice());
                    order.setCount(1);
                    order.setPrice(menuDataSingle.getPrice());
                    orders.add(order);
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(orders);
                    prefsEditor.putString("Order", json);
                    prefsEditor.commit();

                }else{

                    String json = mPrefs.getString("Order", "");
                    Order orders[]  = gsonKontrolOrder.fromJson(json, Order[].class);
                    boolean aynıSiparisMi = false;
                    for(int i=0;i<orders.length;i++){
                        if(orders[i].getId()==menuDataSingle.getId()){
                            orders[i].setTotal(orders[i].getTotal()+menuDataSingle.getPrice());
                            orders[i].setCount(orders[i].getCount()+1);
                            aynıSiparisMi=true;
                        }
                    }
                    if(!aynıSiparisMi){

                        Order order = new Order();
                        order.setName(menuDataSingle.getName());
                        order.setMenu_id(menuDataSingle.getMenuId());
                        order.setId(menuDataSingle.getId());
                        order.setImg_url(menuDataSingle.getImageUrl());
                        order.setState(1);
                        order.setTotal(menuDataSingle.getPrice());
                        order.setCount(1);
                        orders = orderAdd(orders.length,orders,order);



                    }
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String jsonNew = gson.toJson(orders);
                    prefsEditor.putString("Order", jsonNew);
                    prefsEditor.commit();



                    /*mPrefs = mContext.getSharedPreferences("MobilHotelInfo" ,  Context.MODE_PRIVATE);
                    mPrefs.edit().remove("Order").commit();*/
                }





                Toast.makeText(mContext,menuDataSingle.getName()+" sepete  eklendi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuDrinkData.size();

    }

    public static Order[] orderAdd(int n, Order arr[], Order x)
        {
            int i;

            Order newarr[] = new Order[n + 1];

            for (i = 0; i < n; i++)
                newarr[i] = arr[i];

            newarr[n] = x;

            return newarr;
     }

}
