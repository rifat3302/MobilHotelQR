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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilhotelqr.Fragment.FragmentLastOrder;
import com.example.mobilhotelqr.Models.Order;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.mobilhotelqr.Constant.IMAGE_URL;

public class LastOrderAdapter extends RecyclerView.Adapter<LastOrderAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context mContext;

    private View mView;

    private FragmentManager myContextFragment;

    private  Button botton ;
    private List<Order> orders;
    private SharedPreferences mPrefs;
    private List<Order> ordersList;
    private float totalPrice;

    public LastOrderAdapter(Context mContext, List<Order> orders, View mView, FragmentManager fm) {
        this.mContext = mContext;
        this.orders = orders;
        this.mView = mView;
        this.myContextFragment = fm;
    }




    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {

        public ImageView product_img;
        public TextView product_name_text;
        public TextView product_count;
        public TextView total;
        public Button buttonUp,buttonDown;


        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            product_img = itemView.findViewById(R.id.imageView5);
            product_name_text = itemView.findViewById(R.id.textView27);
            product_count = itemView.findViewById(R.id.count);
            total = itemView.findViewById(R.id.total);
            buttonUp = itemView.findViewById(R.id.button9);
            buttonDown = itemView.findViewById(R.id.button8);

        }
    }
    @NonNull
    @Override
    public LastOrderAdapter.CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.last_order_cart_tasarim,parent,false);
        return new LastOrderAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {


        Order orderDataSingle = orders.get(position);
        holder.product_name_text.setText(orderDataSingle.getName());
        holder.total.setText(String.valueOf(orderDataSingle.getTotal()));
        holder.product_count.setText(String.valueOf(orderDataSingle.getCount()));
        Picasso.get().load(IMAGE_URL+orderDataSingle.getImg_url()).into(holder.product_img);
        holder.buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botton = mView.findViewById(R.id.button6);
                orders.get(position).setCount(orders.get(position).getCount()+1);
                orders.get(position).setTotal(orders.get(position).getTotal()+orders.get(position).getPrice());
                holder.total.setText(String.valueOf(orders.get(position).getTotal()));
                holder.product_count.setText(String.valueOf(orders.get(position).getCount()));

                mPrefs = mContext.getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("Order", "");

                if(json==null){
                    Toast.makeText(mContext,"Last Order  data is null",Toast.LENGTH_SHORT).show();
                }else{
                    totalPrice = 0;
                    ordersList = new ArrayList<>();
                    for(int i=0; i<orders.size(); i++){
                        Order order = new Order();
                        order.setName(orders.get(i).getName());
                        order.setId(orders.get(i).getId());
                        order.setCount(orders.get(i).getCount());
                        order.setTotal(orders.get(i).getTotal());
                        order.setState(orders.get(i).getState());
                        order.setImg_url(orders.get(i).getImg_url());
                        order.setMenu_id(orders.get(i).getMenu_id());
                        order.setPrice(orders.get(i).getPrice());
                        ordersList.add(order);
                        totalPrice = totalPrice+(orders.get(i).getPrice()*orders.get(i).getCount());
                    }

                    //ilk önce bellekteki xml siliniyor
                    mPrefs.edit().remove("Order").commit();
                   //sonra yeniden oluşturuluyor
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gsonOrder = new Gson();
                    String jsonNew = gsonOrder.toJson(ordersList);
                    prefsEditor.putString("Order", jsonNew);
                    prefsEditor.commit();
                    botton.setText("TOTAL PRICE : "+totalPrice);

                }


            }
        });

        holder.buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botton = mView.findViewById(R.id.button6);
                orders.get(position).setCount(orders.get(position).getCount()-1);
                mPrefs = mContext.getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("Order", "");

                if(orders.get(position).getCount()<=0){

                    orders.removeIf(t->t.getId()==orders.get(position).getId());

                    totalPrice = 0;
                    ordersList = new ArrayList<>();
                    for(int i=0; i<orders.size(); i++){
                        Order order = new Order();
                        order.setName(orders.get(i).getName());
                        order.setId(orders.get(i).getId());
                        order.setCount(orders.get(i).getCount());
                        order.setTotal(orders.get(i).getTotal());
                        order.setState(orders.get(i).getState());
                        order.setImg_url(orders.get(i).getImg_url());
                        order.setMenu_id(orders.get(i).getMenu_id());
                        order.setPrice(orders.get(i).getPrice());
                        ordersList.add(order);
                        totalPrice = totalPrice+(orders.get(i).getPrice()*orders.get(i).getCount());
                    }

                    //ilk önce bellekteki xml siliniyor
                    mPrefs.edit().remove("Order").commit();
                    //sonra yeniden oluşturuluyor
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gsonOrder = new Gson();
                    String jsonNew = gsonOrder.toJson(ordersList);
                    Fragment fragment = new FragmentLastOrder();
                    myContextFragment.beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                    prefsEditor.putString("Order", jsonNew);
                    prefsEditor.commit();
                    botton.setText("TOTAL PRICE : "+totalPrice);


                }else {
                    orders.get(position).setTotal(orders.get(position).getTotal()-orders.get(position).getPrice());
                    holder.total.setText(String.valueOf(orders.get(position).getTotal()));
                    holder.product_count.setText(String.valueOf(orders.get(position).getCount()));
                    if(json==null){
                        Toast.makeText(mContext,"Last Order  data is null",Toast.LENGTH_SHORT).show();
                    }else{
                        totalPrice = 0;
                        ordersList = new ArrayList<>();
                        for(int i=0; i<orders.size(); i++){
                            Order order = new Order();
                            order.setName(orders.get(i).getName());
                            order.setId(orders.get(i).getId());
                            order.setCount(orders.get(i).getCount());
                            order.setTotal(orders.get(i).getTotal());
                            order.setState(orders.get(i).getState());
                            order.setImg_url(orders.get(i).getImg_url());
                            order.setMenu_id(orders.get(i).getMenu_id());
                            order.setPrice(orders.get(i).getPrice());
                            ordersList.add(order);
                            totalPrice = totalPrice+(orders.get(i).getPrice()*orders.get(i).getCount());
                        }

                        //ilk önce bellekteki xml siliniyor
                        mPrefs.edit().remove("Order").commit();
                        //sonra yeniden oluşturuluyor
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gsonOrder = new Gson();
                        String jsonNew = gsonOrder.toJson(ordersList);
                        prefsEditor.putString("Order", jsonNew);
                        prefsEditor.commit();
                        botton.setText("TOTAL PRICE : "+totalPrice);

                    }
                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
