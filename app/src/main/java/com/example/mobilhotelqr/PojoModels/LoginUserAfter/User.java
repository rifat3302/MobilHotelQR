
package com.example.mobilhotelqr.PojoModels.LoginUserAfter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("room_number")
    @Expose
    private Integer roomNumber;
    @SerializedName("tc")
    @Expose
    private String tc;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("private_key")
    @Expose
    private String privateKey;
    @SerializedName("entry_date")
    @Expose
    private String entryDate;
    @SerializedName("exit_date")
    @Expose
    private String exitDate;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("admin_approve")
    @Expose
    private Integer adminApprove;
    @SerializedName("customer_approve")
    @Expose
    private Integer customerApprove;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getAdminApprove() {
        return adminApprove;
    }

    public void setAdminApprove(Integer adminApprove) {
        this.adminApprove = adminApprove;
    }

    public Integer getCustomerApprove() {
        return customerApprove;
    }

    public void setCustomerApprove(Integer customerApprove) {
        this.customerApprove = customerApprove;
    }

}
