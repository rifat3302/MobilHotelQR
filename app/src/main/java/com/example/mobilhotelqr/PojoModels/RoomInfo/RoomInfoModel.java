
package com.example.mobilhotelqr.PojoModels.RoomInfo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RoomInfoModel {

    @SerializedName("roomInfo")
    @Expose
    private List<RoomInfo> roomInfo = null;

    public List<RoomInfo> getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(List<RoomInfo> roomInfo) {
        this.roomInfo = roomInfo;
    }

}
