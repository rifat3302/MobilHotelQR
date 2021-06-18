
package com.example.mobilhotelqr.PojoModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QrPlacesControl {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("name")
    @Expose
    private Object name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

}
