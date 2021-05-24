
package com.example.mobilhotelqr.PojoModels.OrderHistory;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class OrderDetail {

    @SerializedName("order_user_id")
    @Expose
    private Integer orderUserId;
    @SerializedName("menu_id")
    @Expose
    private Integer menuId;
    @SerializedName("menu_sub_id")
    @Expose
    private Integer menuSubId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuSubId() {
        return menuSubId;
    }

    public void setMenuSubId(Integer menuSubId) {
        this.menuSubId = menuSubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
