
package com.example.mobilhotelqr.PojoModels.OrderHistory;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Datum {

    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("order_details")
    @Expose
    private List<OrderDetail> orderDetails = null;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
