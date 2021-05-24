package com.example.mobilhotelqr.Fragment;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mobilhotelqr.Core.ApiUtils;
import com.example.mobilhotelqr.Core.LastOrderAdapter;
import com.example.mobilhotelqr.Core.OrderHistoryAdapter;
import com.example.mobilhotelqr.Core.RetrofitProcess;
import com.example.mobilhotelqr.Models.Order;
import com.example.mobilhotelqr.PojoModels.OrderHistory.Datum;
import com.example.mobilhotelqr.PojoModels.OrderHistory.OrderHistory;
import com.example.mobilhotelqr.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOrderHistory extends Fragment {

    View mView;

    private RecyclerView rvLastOrder ;
    private OrderHistoryAdapter lastOrderAdapter;
    private Button buttonOrder;
    private View mViev;
    private List<Order> ordersList = new ArrayList<>();
    SharedPreferences mPrefs ;
    private AlertDialog alertDialog;


    private RetrofitProcess retrofitProcess;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_order_history,container,false);

        try {
            this.getOrderHistory(mView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mView;
    }

    public void getOrderHistory(View mViews) throws IOException {
        retrofitProcess = ApiUtils.getOrderHistory();
        retrofitProcess.getOrderHistory(123,123).enqueue(new Callback<OrderHistory>() {
            @Override
            public void onResponse(Call<OrderHistory> call, Response<OrderHistory> response) {

                rvLastOrder = mViews.findViewById(R.id.rvOrderHistory);
                rvLastOrder.setHasFixedSize(true);
                rvLastOrder.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                lastOrderAdapter = new OrderHistoryAdapter(getActivity().getBaseContext(),response,mViews,getActivity().getSupportFragmentManager());
                rvLastOrder.setAdapter(lastOrderAdapter);

            }

            @Override
            public void onFailure(Call<OrderHistory> call, Throwable t) {
                int b = 0;
            }
        });

    }
}
