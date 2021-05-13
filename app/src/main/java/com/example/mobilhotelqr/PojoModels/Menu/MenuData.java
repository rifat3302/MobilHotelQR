
package com.example.mobilhotelqr.PojoModels.Menu;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MenuData {

    @SerializedName("meat")
    @Expose
    private List<Meat> meat = null;
    @SerializedName("drink")
    @Expose
    private List<Drink> drink = null;
    @SerializedName("snack")
    @Expose
    private List<Snack> snack = null;

    public List<Meat> getMeat() {
        return meat;
    }

    public void setMeat(List<Meat> meat) {
        this.meat = meat;
    }

    public List<Drink> getDrink() {
        return drink;
    }

    public void setDrink(List<Drink> drink) {
        this.drink = drink;
    }

    public List<Snack> getSnack() {
        return snack;
    }

    public void setSnack(List<Snack> snack) {
        this.snack = snack;
    }

}
