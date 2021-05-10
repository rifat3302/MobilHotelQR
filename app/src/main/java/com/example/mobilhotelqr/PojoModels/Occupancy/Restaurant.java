
package com.example.mobilhotelqr.PojoModels.Occupancy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Restaurant {

    @SerializedName("ca")
    @Expose
    private Integer ca;
    @SerializedName("co")
    @Expose
    private Integer co;
    @SerializedName("percent")
    @Expose
    private Integer percent;

    public Integer getCa() {
        return ca;
    }

    public void setCa(Integer ca) {
        this.ca = ca;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

}
