
package com.example.mobilhotelqr.PojoModels.Occupancy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("pool")
    @Expose
    private Pool pool;
    @SerializedName("pub")
    @Expose
    private Pub pub;
    @SerializedName("sauna")
    @Expose
    private Sauna sauna;
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;
    @SerializedName("gym")
    @Expose
    private Gym gym;
    @SerializedName("hotel")
    @Expose
    private Hotel hotel;

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Pub getPub() {
        return pub;
    }

    public void setPub(Pub pub) {
        this.pub = pub;
    }

    public Sauna getSauna() {
        return sauna;
    }

    public void setSauna(Sauna sauna) {
        this.sauna = sauna;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
