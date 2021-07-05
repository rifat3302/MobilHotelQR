
package com.example.mobilhotelqr.PojoModels.UserInfo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserInfo {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("date")
    @Expose
    private Integer date;
    @SerializedName("room_number")
    @Expose
    private Integer roomNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("exit_date")
    @Expose
    private String exitDate;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;

    }
}
