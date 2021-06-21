
package com.example.mobilhotelqr.PojoModels.GooglePlaces;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Place {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name_head")
    @Expose
    private String nameHead;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("type")
    @Expose
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameHead() {
        return nameHead;
    }

    public void setNameHead(String nameHead) {
        this.nameHead = nameHead;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
