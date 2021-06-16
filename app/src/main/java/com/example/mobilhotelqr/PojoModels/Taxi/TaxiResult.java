
package com.example.mobilhotelqr.PojoModels.Taxi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaxiResult {

    @SerializedName("taxi")
    @Expose
    private List<Taxi> taxi = null;

    public List<Taxi> getTaxi() {
        return taxi;
    }

    public void setTaxi(List<Taxi> taxi) {
        this.taxi = taxi;
    }

}
