package com.example.mobilhotelqr.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.LastOrderAdapter;
import com.example.mobilhotelqr.Core.LoadingDialog;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.DashboardFragment;
import com.example.mobilhotelqr.Models.Order;
import com.example.mobilhotelqr.PojoModels.Response.ResponseData;
import com.example.mobilhotelqr.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLastOrder  extends Fragment {

    private RecyclerView rvLastOrder ;
    private LastOrderAdapter lastOrderAdapter;
    private Button buttonOrder;
    private View mViev;
    private List<Order> ordersList = new ArrayList<>();
    SharedPreferences mPrefs ;
    private AlertDialog alertDialog;


    private RetrofitProcess retrofitProcess;
    private float totalPrice=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViev = inflater.inflate(R.layout.fragment_last_order,container,false);

        buttonOrder = mViev.findViewById(R.id.button6);


        mPrefs = this.getActivity().getSharedPreferences("MobilHotelInfo", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Order", "");
        Order[] orders = gson.fromJson(json, Order[].class);
        if(json==null){
            Toast.makeText(getActivity().getBaseContext(),"Order  Fragment  data is null",Toast.LENGTH_SHORT).show();
        }else{

            for(int i=0; i<orders.length; i++){
                   Order order = new Order();
                   order.setName(orders[i].getName());
                   order.setId(orders[i].getId());
                   order.setCount(orders[i].getCount());
                   order.setTotal(orders[i].getTotal());
                   order.setState(orders[i].getState());
                   order.setImg_url(orders[i].getImg_url());
                   order.setMenu_id(orders[i].getMenu_id());
                   order.setPrice(orders[i].getPrice());
                   ordersList.add(order);
                   totalPrice = totalPrice+order.getTotal();
            }

            rvLastOrder = mViev.findViewById(R.id.rvLastOrder);
            rvLastOrder.setHasFixedSize(true);
            rvLastOrder.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            lastOrderAdapter = new LastOrderAdapter(getActivity().getBaseContext(),ordersList,mViev,getActivity().getSupportFragmentManager());
            rvLastOrder.setAdapter(lastOrderAdapter);
        }

        buttonOrder.setText("Total Price : "+totalPrice);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalPrice>0){

                    LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                    loadingDialog.startLoadingDialog();
                    JSONObject jsonOrder = new JSONObject();

                    JSONArray array = new JSONArray();

                    Gson gson = new Gson();
                    String json = mPrefs.getString("Order", "");
                    Order[] orders = gson.fromJson(json, Order[].class);

                    for (Order o : orders) {
                        JSONObject item = new JSONObject();
                        try {
                            item.put("menu_id",o.getId());
                            item.put("menu_sub_id",o.getMenu_id());
                            item.put("count",o.getCount());
                            item.put("total",o.getTotal());
                            array.put(item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        //TODO burası login olduktan sonra dinamik set edilecek
                        JSONObject item = new JSONObject();
                        item.put("user_id",123);
                        item.put("room_number",123);
                        item.put("total",totalPrice);
                        item.put("state",1);
                        jsonOrder.put("order_data",array);
                        jsonOrder.put("user_data",item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    retrofitProcess = ApiUtils.saveOrder();
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(jsonOrder).toString());
                    retrofitProcess.saveOrder(body).enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                           loadingDialog.dismissDialog();
                            AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
                            LayoutInflater inflater = getActivity().getLayoutInflater();
                            ab.setView(inflater.inflate(R.layout.save_order_success,null));
                            ab.setCancelable(false);
                            alertDialog = ab.create();
                            alertDialog.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mPrefs = getActivity().getSharedPreferences("MobilHotelInfo" ,  Context.MODE_PRIVATE);
                                    mPrefs.edit().remove("Order").commit();
                                    Fragment fragment = new DashboardFragment();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                                    alertDialog.dismiss();
                                }
                            },1000);


                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                            int b =0;
                        }
                    });
                }
            }
        });

        return mViev;
    }
}
