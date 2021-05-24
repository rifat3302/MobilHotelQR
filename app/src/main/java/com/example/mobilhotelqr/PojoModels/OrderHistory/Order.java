
package com.example.mobilhotelqr.PojoModels.OrderHistory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Order {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("room_number")
    @Expose
    private Integer roomNumber;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("state")
    @Expose
    private Integer state;
    @SerializedName("order_date")
    @Expose
    private String orderDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

}
